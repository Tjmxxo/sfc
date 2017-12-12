package cc.xpress.dao.impl;

import cc.xpress.bean.dto.HallTbDTO;
import cc.xpress.dao.IHallDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("hallDAO")
public class HallDAOImpl extends BaseDAOImpl<HallTbDTO> implements IHallDAO {


    @Override
    public List<HallTbDTO> getAllHall(int cinemaId) {
        String hql = "from HallTbDTO where cinemaTbDTO.cinemaId = ?";
        return sessionFactory.getCurrentSession().createQuery
                (hql, HallTbDTO.class).setParameter(0, cinemaId).
                list();
    }

    @Override
    public HallTbDTO getHallById(int hallId, int cinemaId) {
        String hql = "from HallTbDTO where cinemaTbDTO.cinemaId = ? and hallId = ?";
        return sessionFactory.getCurrentSession().createQuery(hql, HallTbDTO.class).
                setParameter(0, cinemaId).
                setParameter(1, hallId).
                uniqueResult();
    }
}
