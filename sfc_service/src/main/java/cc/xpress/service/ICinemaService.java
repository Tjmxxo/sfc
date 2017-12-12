package cc.xpress.service;

import cc.xpress.bean.dto.CinemaTbDTO;
import cc.xpress.bean.vo.CinemaVo;

import java.io.Serializable;
import java.util.List;

public interface ICinemaService {
    /**
     * 通过影院cinemaId查询影院
     *
     * @param cinemaId
     * @return
     */
    CinemaTbDTO getCinema(String cinemaId) throws NumberFormatException;

    /**
     * 通过城市编号cityId查询影院
     *
     * @param cityId
     * @return
     */
    List<CinemaTbDTO> getAllCinemaList(String cityId) throws NumberFormatException;

    /**
     * 根据是否数据data中有编号选择添加或者修改影院
     *
     * @param cinemaVo
     * @return
     */
    Serializable saveCinema(CinemaVo cinemaVo);

    /**
     * 根据多个影院CinemaId删除影院
     *
     * @param cinemaIds
     * @return
     */
    List<Integer> deleteCinema(String[] cinemaIds) throws NumberFormatException, IllegalArgumentException;

    /**
     * 根据Cinemaid删除影院
     *
     * @param cinemaId
     * @return
     * @throws NumberFormatException
     * @throws IllegalArgumentException
     */
    Integer deleteOne(String cinemaId) throws NumberFormatException, IllegalArgumentException;

    /**
     * 根据movieI的和cityId查询电影院
     *
     * @param movieId
     * @param cityId
     * @return
     */
    CinemaTbDTO[] getCinemaByCityAndMovie(String movieId, String cityId);
}
