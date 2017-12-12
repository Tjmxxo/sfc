package cc.xpress.service;

import cc.xpress.bean.dto.OrderTbDTO;
import cc.xpress.bean.dto.UserTbDTO;

import java.util.List;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-04 10:27
 * @modified By:
 */
public interface IOrderService {
    /**
     * 查询未支付的订单数量
     *
     * @param userTbDTO
     * @return
     */
    int getNonPaymentOrderCount(UserTbDTO userTbDTO);

    /**
     * 创建订单
     *
     * @param userTel
     * @param seatIds
     * @param planId
     * @param userTbDTO
     * @return
     */
    OrderTbDTO createOrder(String userTel, String seatIds, String planId, UserTbDTO userTbDTO);

    /**
     * 通过电影院获取所有订单
     *
     * @return
     */
    List<OrderTbDTO> getAllOrderByCinemaId(int cinemaId);

    /**
     * @param userTbDTO
     * @param orderStatus
     * @return
     */
    List<OrderTbDTO> getUserOrder(UserTbDTO userTbDTO, String orderStatus) throws IllegalAccessException;

    /**
     * 取消订单
     *
     * @param userTbDTO
     * @param orderId
     * @return
     */
    int cancelOrder(UserTbDTO userTbDTO, String orderId) throws IllegalAccessException, NullPointerException, IllegalArgumentException;

    /**
     * 查询用户未支付订单
     *
     * @param orderId
     * @param userTbDTO
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    OrderTbDTO getNonPaymentOrder(String orderId, UserTbDTO userTbDTO) throws IllegalAccessException, IllegalArgumentException, NullPointerException;

    /**
     * 支付订单
     *
     * @param userTbDTO
     * @param orderId
     * @param couponId
     * @param useVipCard
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    int payOrder(UserTbDTO userTbDTO, String orderId, String payMethod, String couponId, boolean useVipCard) throws IllegalAccessException, IllegalArgumentException, NullPointerException;
}