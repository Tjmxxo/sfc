package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.CinemaTbDTO;
import cc.xpress.bean.dto.CityTbDTO;
import cc.xpress.bean.dto.MovieTbDTO;
import cc.xpress.bean.dto.PlanTbDTO;
import cc.xpress.bean.vo.MovieVo;
import cc.xpress.bean.vo.Node;
import cc.xpress.config.PlanStatus;
import cc.xpress.dao.ICinemaDAO;
import cc.xpress.dao.IMovieDAO;
import cc.xpress.dao.IPlanDAO;
import cc.xpress.service.IMovieService;
import cc.xpress.utils.CommonUtils;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-02 14:24
 * @modified By:
 */
@Service("movieService")
public class MovieServiceImpl extends BaseServiceImpl<MovieTbDTO> implements IMovieService {

    @Autowired
    IMovieDAO movieDAO;

    @Autowired
    IPlanDAO planDAO;

    @Autowired
    ICinemaDAO cinemaDAO;

    @Override
    @Transactional
    public MovieTbDTO[] getCurrentCityMovie(String cityId) {
        if (!CommonUtils.isConvertedToNumber(cityId)) {
            return null;
        }
        int id = Integer.parseInt(cityId);
        List<PlanTbDTO> planList = planDAO.getPlanByCityIdGroupByMovieId(id, PlanStatus.ENABLE_STATUE);
        int size = planList.size();
        if (size == 0) {
            return null;
        } else {
            MovieTbDTO[] movieArray = new MovieTbDTO[size];
            for (int i = 0; i < size; i++) {
                movieArray[i] = planList.get(i).getMovieTbDTO();
            }
            return movieArray;
        }
    }

    /**
     * 获取当前影院所有电影
     *
     * @return
     */
    @Transactional
    @Override
    public List<MovieTbDTO> getAllMovieByCinemaId(int cinemaId) {
        if (cinemaId == 0) {
            return null;
        }
        List<MovieTbDTO> from1 = movieDAO.query("from", MovieTbDTO.class).list();
        return from1;
    }

    @Transactional
    @Override
    public MovieTbDTO getMovie(String movieId) throws NumberFormatException {
        return null;
    }

    @Transactional
    @Override
    public MovieTbDTO getMovieByName(String movieId) {
        return null;
    }

    @Override
    public List<MovieTbDTO> getMovieByDate(String date) {
        return null;
    }

    @Transactional
    @Override
    public List<MovieTbDTO> getMovieList(String no) {
        return null;
    }

    @Transactional
    @Override
    public Serializable saveMovie(MovieTbDTO movieTbDTO) {
        return movieDAO.saveEntity(movieTbDTO);
    }

    @Transactional
    @Override
    public List<Integer> deleteMovie(String[] movieIds) throws NumberFormatException, IllegalArgumentException {
        return null;
    }

    @Transactional
    @Override
    public Integer deleteOne(String movieId) throws NumberFormatException, IllegalArgumentException {
        return null;
    }
}
