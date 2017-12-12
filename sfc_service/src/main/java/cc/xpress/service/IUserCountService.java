package cc.xpress.service;

import cc.xpress.bean.vo.MovieVo;

import java.util.List;

/**
 * @Create By Tjmxxo
 */
public interface IUserCountService {
    /**
     * 获取当前电影院的电影订单人数/分组
     *
     * @param cinemaId
     * @return
     */
    List<MovieVo> getMovieCount(int cinemaId);
}
