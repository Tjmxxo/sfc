package cc.xpress.service;

import cc.xpress.bean.dto.MovieTbDTO;
import cc.xpress.bean.vo.MovieVo;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-02 14:17
 * @modified By:
 */
public interface IMovieService extends IBaseService<MovieTbDTO> {
    /**
     * 查询当前城市可购买的的电影
     *
     * @return
     */
    MovieTbDTO[] getCurrentCityMovie(String CityId);

    /**
     * 获取当前影院所有电影
     *
     * @return
     */
    List<MovieTbDTO> getAllMovieByCinemaId(int cinemaId);

    /**
     * 通过电影movieId查询电影
     *
     * @param movieId
     * @return
     */
    MovieTbDTO getMovie(String movieId) throws NumberFormatException;

    /**
     * 查询所有电影
     *
     * @return
     */
    List<MovieTbDTO> getMovieList(String no) throws NumberFormatException;

    /**
     * 电影名查电影
     *
     * @param movieName
     * @return
     */
    MovieTbDTO getMovieByName(String movieName);

    /**
     * 日期查电影
     *
     * @param date
     * @return
     */
    List<MovieTbDTO> getMovieByDate(String date);

    /**
     * 根据是否数据data中有编号选择添加或者修改电影
     *
     * @param movieDTO
     * @return
     */
    Serializable saveMovie(MovieTbDTO movieDTO);

    /**
     * 根据多个电影movieId删除电影
     *
     * @param movieIds
     * @return
     */
    List<Integer> deleteMovie(String[] movieIds) throws NumberFormatException, IllegalArgumentException;

    /**
     * 根据movieid删除电影
     *
     * @param movieId
     * @return
     * @throws NumberFormatException
     * @throws IllegalArgumentException
     */
    Integer deleteOne(String movieId) throws NumberFormatException, IllegalArgumentException;


}
