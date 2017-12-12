package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.*;
import cc.xpress.bean.vo.Node;
import cc.xpress.bean.vo.RabbitMassage;
import cc.xpress.config.CommonConfig;
import cc.xpress.config.CommonNotice;
import cc.xpress.config.OrderStatus;
import cc.xpress.config.SelectStatus;
import cc.xpress.dao.IOrderDAO;
import cc.xpress.dao.IPlanDAO;
import cc.xpress.dao.ISeatDAO;
import cc.xpress.dao.ISelectDAO;
import cc.xpress.service.IMQProducer;
import cc.xpress.service.IOrderService;
import cc.xpress.utils.CommonUtils;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-04 10:28
 * @modified By:
 */
@Service("orderService")
public class OrderServiceImpl  implements IOrderService{
    Logger logger = Logger.getLogger(OrderServiceImpl.class);
    @Autowired
    IOrderDAO orderDAO;
    //    @Autowired
//    ShardedJedisPool shardedJedisPool;
    @Autowired
    IPlanDAO planDAO;
    @Autowired
    ISeatDAO seatDAO;
    @Autowired
    ISelectDAO selectDAO;
//    @Autowired
//    IMQProducer imqProducer;

    /**
     * 查询未支付的订单数量
     *
     * @param userTbDTO
     * @return
     */
    @Transactional
    @Override
    public int getNonPaymentOrderCount(UserTbDTO userTbDTO) {
        if (userTbDTO == null || userTbDTO.getUserId() == null) {
            return -1;
        }
        Node status = new Node("orderStatus", OrderStatus.NON_PAYMENT_ORDER);
        Node userId = new Node("userTbDTO.userId", userTbDTO.getUserId());
        Query query = orderDAO.query("select count(*) from", OrderTbDTO.class, status, userId);
        Long count = (Long) query.uniqueResult();
        return count.intValue();
    }

    /**
     * 提交订单
     *
     * @param userTel
     * @param seatIds
     * @param planId
     * @param userTbDTO
     * @return
     * @throws TimeoutException
     */
    @Override
    @Transactional
    public OrderTbDTO createOrder(String userTel, String seatIds, String planId, UserTbDTO userTbDTO) {
        if (!CommonUtils.isConvertedToNumber(planId)) {
            //TODO 异常描述
            throw new IllegalArgumentException();
        }
        int pid = Integer.parseInt(planId);
        /*查询plan*/
        PlanTbDTO planTbDTO = planDAO.getEntityById(PlanTbDTO.class, pid);
        if (planTbDTO == null) {
            //TODO 异常描述
            throw new NullPointerException();
        }
        /*获取订单价格*/
        int price = planTbDTO.getPlanPrice();
        String[] split = seatIds.split(",");
        /*创建用户选座set集合*/
        HashSet<OrderSeatTbDTO> orderSeatTbDTOS = new HashSet<>();
        int sumPrice = 0;
        Long[] longs = new Long[split.length];
        for (int i=0;i< split.length ;i++) {
            if (!CommonUtils.isConvertedToNumber(split[i])) {
                /*判断order是否非法*/
                //TODO 异常描述
                throw new IllegalArgumentException();
            }
            SelectTbDTO selectWithLock = selectDAO.getSelectWithLock(pid,Integer.parseInt(split[i]));
            if (selectWithLock == null) {
                //TODO 异常描述
                throw new NullPointerException("该座位不存在或已经被购买");
            }
            selectWithLock.setSeatStatus(1);
            longs[i]=selectWithLock.getSelectId();
            sumPrice += selectWithLock.getPlanTbDTO().getPlanPrice();
            orderSeatTbDTOS.add(new OrderSeatTbDTO(price, selectWithLock.getSeatTbDTO()));
        }
        OrderTbDTO orderTbDTO = new OrderTbDTO();
        if (userTbDTO == null || userTbDTO.getUserId() == null) {
            throw new NullPointerException(CommonNotice.USER_NOT_LOGIN_IN);
        }
        if (!userTbDTO.getUserTel().equals(userTel)) {
            //TODO
            throw new NullPointerException("非法提交电话号码");
        }
        /*创建订单*/
        orderTbDTO.setUserTel(userTel);
        orderTbDTO.setUserTbDTO(userTbDTO);
        orderTbDTO.setPlanTbDTO(planTbDTO);
        orderTbDTO.setOrderStatus(OrderStatus.NON_PAYMENT_ORDER);
        orderTbDTO.setOrderSeatTbDTOS(orderSeatTbDTOS);
        orderTbDTO.setHallTbDTO(planTbDTO.getHallTbDTO());
        orderTbDTO.setCinemaTbDTO(planTbDTO.getCinemaTbDTO());
        orderTbDTO.setMovieTbDTO(planTbDTO.getMovieTbDTO());
        orderTbDTO.setOrderSumPrice(sumPrice);
        Serializable serializable = orderDAO.saveEntity(orderTbDTO);
        OrderTbDTO orderInfo = orderDAO.getOrderInfo(serializable);
        /*创建Rabbit 延时队列*/
        RabbitMassage rabbitMassage = new RabbitMassage();
        rabbitMassage.setMessageType(CommonConfig.RABBITMQ_ORDER_ID);
        rabbitMassage.setMessageId((Integer) serializable);
        rabbitMassage.setSelectIds(longs);
        rabbitMassage.setMessageCreateTime(System.currentTimeMillis());
        logger.info(rabbitMassage);
//        imqProducer.sendDataToQueue(rabbitMassage);
        return orderInfo;
    }

    /**
     * 通过电影院获取所有订单
     *
     * @param cinemaId
     * @return
     */
    @Override
    @Transactional
    public List<OrderTbDTO> getAllOrderByCinemaId(int cinemaId) {
        List<OrderTbDTO> allOrderByCineamId = orderDAO.getAllOrderByCineamId(cinemaId);
        if (allOrderByCineamId == null) {
            return null;
        }
        return allOrderByCineamId;
    }

    /**
     * 根据用户id 和订单状态查询订单
     *
     * @param userTbDTO
     * @param orderStatus
     * @return
     */
    @Override
    @Transactional
    public List<OrderTbDTO> getUserOrder(UserTbDTO userTbDTO, String orderStatus) throws IllegalAccessException, IllegalArgumentException, NullPointerException {
        if (userTbDTO == null || userTbDTO.getUserId() == null) {
            throw new NullPointerException(CommonNotice.USER_NOT_LOGIN_IN);
        }
        if (orderStatus == null) {
            return orderDAO.getUserOrder(userTbDTO.getUserId(), null);
        } else {
            if (!CommonUtils.isConvertedToNumber(orderStatus)) {
                //TODO 异常描述
                throw new IllegalArgumentException();
            }
            Field[] fields = OrderStatus.class.getDeclaredFields();
            for (Field field : fields) {
                int status = (int) field.get(null);
                int oStatus = Integer.parseInt(orderStatus);
                if (status == oStatus) {
                    return orderDAO.getUserOrder(userTbDTO.getUserId(), oStatus);
                }
            }
            //TODO
            throw new IllegalArgumentException();
        }
    }

    /**
     * 取消用户订单
     *
     * @param userTbDTO
     * @param orderId
     * @return
     */
    @Override
    @Transactional
    public int cancelOrder(UserTbDTO userTbDTO, String orderId) throws IllegalAccessException, NullPointerException, IllegalArgumentException {
        if (userTbDTO == null || userTbDTO.getUserId() == null) {
            throw new NullPointerException(CommonNotice.USER_NOT_LOGIN_IN);
        }
        if (!CommonUtils.isConvertedToNumber(orderId)) {
            throw new IllegalArgumentException();
        }
        OrderTbDTO entityById = orderDAO.getEntityById(OrderTbDTO.class, Integer.parseInt(orderId));
        if (entityById == null) {
            //TODO
            throw new NullPointerException("订单不存在");
        }
        int userId = entityById.getUserTbDTO().getUserId();
        if (userId != userTbDTO.getUserId()) {
            //TODO
            throw new IllegalAccessException("该订单不是您的订单无法取消");
        }
        Set<OrderSeatTbDTO> orderSeatTbDTOS = entityById.getOrderSeatTbDTOS();
        Iterator<OrderSeatTbDTO> iterator = orderSeatTbDTOS.iterator();
        while(iterator.hasNext()){
            OrderSeatTbDTO next = iterator.next();
            SelectTbDTO selectByPlanAndSeatId = selectDAO.getSelectByPlanAndSeatId(next.getSeatTbDTO().getSeatId(), entityById.getPlanTbDTO().getPlanId());
            if (selectByPlanAndSeatId.getSeatStatus()!=SelectStatus.SELECTED_STATUS){
                throw new IllegalArgumentException("座位已经释放，不能重复取消订单");
            }
            selectByPlanAndSeatId.setSeatStatus(SelectStatus.ENABLE_STATUE);
        }
        entityById.setOrderStatus(OrderStatus.CANCEL_ORDER);
        orderDAO.updateEntity(entityById);
        return 0;
    }

    /**
     * 获取用户未支付订单
     *
     * @return
     */
    @Transactional
    @Override
    public OrderTbDTO getNonPaymentOrder(String orderId, UserTbDTO userTbDTO) throws IllegalAccessException, IllegalArgumentException, NullPointerException {
        if (userTbDTO == null || userTbDTO.getUserId() == null) {
            throw new NullPointerException(CommonNotice.USER_NOT_LOGIN_IN);
        }
        if (!CommonUtils.isConvertedToNumber(orderId)) {
            //TODO
            throw new IllegalArgumentException("非法订单id");
        }
        OrderTbDTO orderInfo = orderDAO.getOrderInfo(Integer.parseInt(orderId));
        if (orderInfo.getOrderStatus() != 0) {
            //TODO
            throw new IllegalArgumentException("订单已支付/关闭");
        }
        if (!userTbDTO.getUserId().equals(orderInfo.getUserTbDTO().getUserId())) {
            //TODO
            throw new IllegalAccessException("该订单不是您的订单无法访问");
        }
        return orderInfo;
    }

    /**
     * 支付订单
     *
     * @param userTbDTO
     * @param orderId
     * @param couponId
     * @param useVipCard
     * @return
     */
    @Override
    @Transactional
    public int payOrder(UserTbDTO userTbDTO, String orderId, String payMethod, String couponId, boolean useVipCard) throws IllegalAccessException, IllegalArgumentException, NullPointerException {
        if (userTbDTO == null || userTbDTO.getUserId() == null) {
            throw new NullPointerException(CommonNotice.USER_NOT_LOGIN_IN);
        }
        if (!CommonUtils.isConvertedToNumber(orderId)) {
            //TODO
            throw new IllegalArgumentException("非法订单id");
        }
        OrderTbDTO orderInfo = orderDAO.getEntityById(OrderTbDTO.class, Integer.parseInt(orderId));
        if (orderInfo.getOrderStatus() != 0) {
            //TODO
            throw new IllegalArgumentException("订单已支付/关闭");
        }
        if (!userTbDTO.getUserId().equals(orderInfo.getUserTbDTO().getUserId())) {
            //TODO
            throw new IllegalAccessException("该订单不是您的订单无法访问");
        }
        orderInfo.setOrderStatus(1);
        orderInfo.setOrderPayMethod(payMethod);
        orderInfo.setOrderPayTime(new Timestamp(System.currentTimeMillis()));
        orderInfo.setOrderPayPrice(orderInfo.getOrderSumPrice());
        orderDAO.updateEntity(orderInfo);
        return 0;
    }
}