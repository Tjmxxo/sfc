package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.MovieTbDTO;
import cc.xpress.bean.dto.OrderTbDTO;
import cc.xpress.bean.vo.MovieVo;
import cc.xpress.dao.IMovieDAO;
import cc.xpress.dao.IOrderDAO;
import cc.xpress.service.IUserCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Create By Tjmxxo
 */
@Service("userCountService")
public class UserCountServiceImpl implements IUserCountService {

    @Autowired
    private IMovieDAO movieDAO;

    @Autowired
    private IOrderDAO orderDAO;

    /**
     * 获取当前电影院的电影订单人数/分组
     *
     * @param cinemaId
     * @return
     */
    @Transactional
    @Override
    public List<MovieVo> getMovieCount(int cinemaId) {
        if (cinemaId == 0) {
            return null;
        }
        List<MovieTbDTO> allMovieIdByCinemaId = movieDAO.getAllMovieIdByCinemaId();
        if (allMovieIdByCinemaId.size() == 0) {
            return null;
        }
        Iterator<MovieTbDTO> iterator = allMovieIdByCinemaId.iterator();
        ArrayList<MovieVo> movieVos = new ArrayList<>();
        while (iterator.hasNext()) {
            MovieTbDTO next = iterator.next();
            List<OrderTbDTO> countByMovieIdCinemaId = orderDAO.getCountByMovieIdCinemaId(cinemaId, next.getMovieId());
            Iterator<OrderTbDTO> iterator1 = countByMovieIdCinemaId.iterator();
            int i = 0;
            while (iterator1.hasNext()) {
                OrderTbDTO next1 = iterator1.next();
                i += next1.getOrderSeatTbDTOS().size();
            }
            movieVos.add(new MovieVo(next, i));
        }
        return movieVos;
    }
}
