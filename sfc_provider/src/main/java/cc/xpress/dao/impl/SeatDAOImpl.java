package cc.xpress.dao.impl;

import cc.xpress.bean.dto.SeatTbDTO;
import cc.xpress.dao.ISeatDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("seatDAO")
public class SeatDAOImpl extends BaseDAOImpl<SeatTbDTO> implements ISeatDAO {


    @Override
    public List<SeatTbDTO> getAllSeatById(int hallId) {
        return sessionFactory.getCurrentSession().createQuery
                ("from SeatTbDTO s where s.hallTbDTO.hallId = ?", SeatTbDTO.class).
                setParameter(0, hallId).
                list();
    }
}
