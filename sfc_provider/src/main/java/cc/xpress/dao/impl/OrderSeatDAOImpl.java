package cc.xpress.dao.impl;

import cc.xpress.bean.dto.OrderSeatTbDTO;
import cc.xpress.dao.IOrderSeatDAO;
import org.springframework.stereotype.Repository;

@Repository("orderSeatDAO")
public class OrderSeatDAOImpl extends BaseDAOImpl<OrderSeatTbDTO> implements IOrderSeatDAO {


    @Override
    public OrderSeatTbDTO getAllOrderSeatBySeatId(int seatId) {
        String hql = "from OrderSeatTbDTO orders join fetch orders.orderTbDTO where orders.seatTbDTO.seatId = ?";
        return sessionFactory.getCurrentSession().createQuery
                (hql, OrderSeatTbDTO.class).
                setParameter(0, seatId).
                uniqueResult();
    }
}
