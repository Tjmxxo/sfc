package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.OrderTbDTO;
import cc.xpress.bean.dto.SelectTbDTO;
import cc.xpress.bean.vo.RabbitMassage;
import cc.xpress.config.CommonConfig;
import cc.xpress.config.CommonNotice;
import cc.xpress.config.OrderStatus;
import cc.xpress.config.SelectStatus;
import cc.xpress.dao.IOrderDAO;
import cc.xpress.dao.ISelectDAO;
import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @author: Robben.Hu
 * @Description: 监听rabbit消息队列，实现自动取消订单的功能
 * @Date: Created in 2017-12-06 14:26
 * @modified By:
 */
@Component("queueListener")
public class QueueListener implements MessageListener {
    @Autowired
    IOrderDAO orderDAO;
    @Autowired
    ISelectDAO iSelectDAO;
    Logger logger=Logger.getLogger(QueueListener.class);
    @Override
    @Transactional
    public void onMessage(Message message) {
        try {
            String json = new String(message.getBody());
            logger.info(json);
            RabbitMassage parse = JSON.parse(json, RabbitMassage.class);
            if (parse == null || parse.getMessageId() == null || !CommonConfig.RABBITMQ_ORDER_ID.equals(parse.getMessageType())) {
                //TODO
                throw new IllegalArgumentException("非法取消订单的请求");
            }
            OrderTbDTO entityById = orderDAO.getEntityById(OrderTbDTO.class, parse.getMessageId());
            if (!entityById.getOrderStatus().equals(OrderStatus.NON_PAYMENT_ORDER)) {
                throw new NullPointerException(CommonNotice.ORDER_IS_CANCELED);
            }
            Long[] selectIds = parse.getSelectIds();
            for(Long selectId:selectIds){
                SelectTbDTO selectTbDTO = iSelectDAO.getEntityById(SelectTbDTO.class, selectId);
                if(selectTbDTO==null||selectTbDTO.getSeatStatus()!=SelectStatus.SELECTED_STATUS){
                    throw new NullPointerException(CommonNotice.ORDER_IS_CANCELED);
                }
                selectTbDTO.setSeatStatus(SelectStatus.ENABLE_STATUE);
                iSelectDAO.updateEntity(selectTbDTO);
            }
            entityById.setOrderStatus(OrderStatus.CANCEL_ORDER);
            orderDAO.updateEntity(entityById);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
