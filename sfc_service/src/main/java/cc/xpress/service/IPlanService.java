package cc.xpress.service;

import cc.xpress.bean.dto.PlanTbDTO;
import cc.xpress.bean.vo.PlanVo;

import java.text.ParseException;
import java.util.List;

/**
 * @Create By Tjmxxo
 */
public interface IPlanService {

    List<PlanVo> getTodayPlanList(int cinemaId) throws ParseException;

    List<PlanVo> getAllPlanList(int cinemaId);

    PlanTbDTO getPlanBtId(int PlanId);

    /**
     * 根据cityId planStatus查询 plan 并且根据 movieId分组
     *
     * @param movieId
     * @param cityId
     * @param cinemaId
     * @return
     * @by robben.hu
     */
    List<PlanTbDTO> getPlanByMovieAndCityAndCinemaGroupByDate(String movieId, String cityId, String cinemaId, String planDate);
}
