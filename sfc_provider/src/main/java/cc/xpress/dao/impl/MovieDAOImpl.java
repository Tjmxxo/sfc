package cc.xpress.dao.impl;

import cc.xpress.bean.dto.MovieTbDTO;
import cc.xpress.dao.IMovieDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("movieDAO")
public class MovieDAOImpl extends BaseDAOImpl<MovieTbDTO> implements IMovieDAO {

    @Override
    public List<MovieTbDTO> getAllMovieByCinemaId(int cinemaId) {
        return sessionFactory.getCurrentSession().createQuery
                ("from MovieTbDTO where CinemaTbDTO.cinemaId = ?", MovieTbDTO.class).setParameter(0, cinemaId).
                list();
    }

    @Override
    public List<MovieTbDTO> getAllMovieIdByCinemaId() {
        return sessionFactory.getCurrentSession().createQuery
                ("from MovieTbDTO movie", MovieTbDTO.class).list();
    }
}
