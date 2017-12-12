package cc.xpress.dao;

import cc.xpress.bean.dto.OrderSeatTbDTO;

public interface IOrderSeatDAO extends IBaseDAO<OrderSeatTbDTO> {

    OrderSeatTbDTO getAllOrderSeatBySeatId(int seatId);

}
