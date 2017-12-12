package cc.xpress.dao.impl;

import cc.xpress.bean.dto.OrderTbDTO;
import cc.xpress.bean.dto.SelectTbDTO;
import cc.xpress.config.OrderStatus;
import cc.xpress.dao.IOrderDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("orderDAO")
public class OrderDAOImpl extends BaseDAOImpl<OrderTbDTO> implements IOrderDAO {


    @Override
    public List<OrderTbDTO> getAllOrderByCineamId(int cinemaId) {
        return sessionFactory.getCurrentSession().createQuery
                ("from OrderTbDTO orders join fetch orders.userTbDTO join fetch orders.planTbDTO join fetch orders.movieTbDTO join fetch orders.hallTbDTO where orders.cinemaTbDTO.cinemaId = ?",
                        OrderTbDTO.class).
                setParameter(0, cinemaId).list();
    }

    @Override
    public List<OrderTbDTO> getCountByMovieIdCinemaId(int cinemaId, int movieId) {
        return sessionFactory.getCurrentSession().createQuery
                ("from OrderTbDTO orders where orders.cinemaTbDTO.cinemaId = ? and orders.movieTbDTO.movieId = ? and orders.orderStatus = ?", OrderTbDTO.class).
                setParameter(0, cinemaId).
                setParameter(1, movieId).
                setParameter(2, OrderStatus.PAID_ORDER).list();
    }

    /**
     * 根据酒店查询关联信息
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderTbDTO getOrderInfo(Serializable orderId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from OrderTbDTO od  join fetch od.planTbDTO join fetch od.movieTbDTO join fetch od.hallTbDTO join fetch od.cinemaTbDTO join fetch od.orderSeatTbDTOS where od.orderId=?");
        OrderTbDTO orderTbDTO = (OrderTbDTO) query.setParameter(0, orderId).uniqueResult();
        return orderTbDTO;
    }

    /**
     * 根据状态和用户id查询订单
     *
     * @param userId
     * @param orderStatus
     * @return
     */
    @Override
    public List<OrderTbDTO> getUserOrder(int userId, Integer orderStatus) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select distinct  od from OrderTbDTO  od join fetch od.planTbDTO join fetch od.movieTbDTO join fetch od.hallTbDTO join fetch od.cinemaTbDTO join fetch od.orderSeatTbDTOS where od.userTbDTO.userId=?";
        if (orderStatus != null) {
            hql += " and od.orderStatus=?";
        }
        hql += " group by od";
        Query query = session.createQuery(hql);
        query.setParameter(0, userId);
        if (orderStatus != null) {
            query.setParameter(1, orderStatus);
        }
        List list = query.list();
        return list;
    }

    @Override
    public List<OrderTbDTO> getAllOrderBy(int planId) {
        String hql = "from OrderTbDTO orders where orders.planTbDTO.planId = ?";
        return sessionFactory.getCurrentSession().createQuery(hql, OrderTbDTO.class).setParameter(0, planId).list();
    }

}
