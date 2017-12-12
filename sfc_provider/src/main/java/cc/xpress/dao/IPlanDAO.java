package cc.xpress.dao;

import cc.xpress.bean.dto.PlanTbDTO;

import java.sql.Date;
import java.util.List;

public interface IPlanDAO extends IBaseDAO<PlanTbDTO> {
    /**
     * 根据影院和日期查询 计划
     *
     * @param cinemaId
     * @param date
     * @return
     */
    List<PlanTbDTO> getPlanTbDTO(int cinemaId, Date date);

    /**
     * 根据影院id查询计划
     *
     * @param cinemaId
     * @return
     */
    List<PlanTbDTO> getPlanTbDTOById(int cinemaId);

    /**
     * 根据cityId 查询 plan 并且根据 movieId分组
     *
     * @param cityId
     * @return
     */
    List<PlanTbDTO> getPlanByCityIdGroupByMovieId(int cityId, int planStatus);

    /**
     * 根据cityId和movieId 查询plan 并且根据cinemaId分组
     *
     * @param movieId
     * @param cityId
     * @param planStatus
     * @return
     */
    List<PlanTbDTO> getPlanByMovieAndCityGroupByCinema(int movieId, int cityId, int planStatus);

    /**
     * 根据cityId planStatus查询 plan 并且根据 movieId分组
     *
     * @param movieId
     * @param cityId
     * @param cinemaId
     * @param planStatus
     * @return
     */
    List<PlanTbDTO> getPlanByMovieAndCityAndCinemaGroupByDate(int movieId, int cityId, int cinemaId, int planStatus);

    /**
     * 根据城市id 电影id 电影院id 计划日期 查询播放场次
     *
     * @param movieId
     * @param cityId
     * @param cinemaId
     * @param planDate
     * @param planStatus
     * @return
     */
    List<PlanTbDTO> getPlanByMovieAndCityAndCinemaAndDate(int movieId, int cityId, int cinemaId, Date planDate, int planStatus);

    /**
     * 根据id查询计划
     *
     * @param planId
     * @return
     */
    PlanTbDTO getPlanInfo(int planId);

    List<PlanTbDTO> getAllPlanStart();

    List<PlanTbDTO> getAllPlanEnd();

    List<PlanTbDTO> getPlanListByTime(int hallId, Date startDate, Date endDate);

    List<PlanTbDTO> getAllPlanByTime(int cinemaId, Date startDate, Date endDate);

}
