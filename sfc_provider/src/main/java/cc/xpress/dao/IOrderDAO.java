package cc.xpress.dao;

import cc.xpress.bean.dto.OrderTbDTO;
import cc.xpress.bean.dto.SelectTbDTO;

import java.io.Serializable;
import java.util.List;

public interface IOrderDAO extends IBaseDAO<OrderTbDTO> {

    List<OrderTbDTO> getAllOrderByCineamId(int cinemaId);

    List<OrderTbDTO> getCountByMovieIdCinemaId(int cinemaId, int movieId);

    /**
     * 根据id查询订单关联信息
     *
     * @param orderId
     * @return
     */
    OrderTbDTO getOrderInfo(Serializable orderId);

    /**
     * 根据状态和用户id查询订单
     *
     * @param userId
     * @param orderStatus
     * @return
     */
    List<OrderTbDTO> getUserOrder(int userId, Integer orderStatus);

    List<OrderTbDTO> getAllOrderBy(int planId);

}
