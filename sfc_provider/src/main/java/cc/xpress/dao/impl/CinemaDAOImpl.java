package cc.xpress.dao.impl;

import cc.xpress.bean.dto.CinemaTbDTO;
import cc.xpress.dao.ICinemaDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cinemaDAO")
public class CinemaDAOImpl extends BaseDAOImpl<CinemaTbDTO> implements ICinemaDAO {

    @Override
    public List<CinemaTbDTO> getAllCinemas() {
        return sessionFactory.getCurrentSession().createQuery("from CinemaTbDTO cinema join fetch cinema.cityTbDTO", CinemaTbDTO.class).list();
    }
}
