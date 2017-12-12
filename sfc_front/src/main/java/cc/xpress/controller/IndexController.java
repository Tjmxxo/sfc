package cc.xpress.controller;

import cc.xpress.bean.dto.*;
import cc.xpress.config.CommonConfig;
import cc.xpress.service.*;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-01 17:39
 * @modified By:
 */
@Controller
@RequestMapping("index")
public class IndexController {
    Logger logger = Logger.getLogger(IndexController.class);
    @Resource(name = "cityService")
    ICityService cityService;
    @Resource(name = "movieService")
    IMovieService movieService;
    @Resource(name = "cinemaService")
    ICinemaService cinemaService;
    @Resource(name = "planService")
    IPlanService planService;
    @Resource(name = "planSeatService")
    IPlanSeatService planSeatService;

    @RequestMapping("index")
    public String getIndex() {
        return "index";
    }

    /**
     * 修改当前
     *
     * @param cityTbDTO
     * @return
     */
    @RequestMapping("change_city")
    public String changeCity(CityTbDTO cityTbDTO) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        try {
            CityTbDTO entityById = cityService.getEntityById(cityTbDTO);
            session.setAttribute(CommonConfig.CURRENT_CITY, entityById);
        } catch (IllegalAccessException | NullPointerException | ClassCastException e) {
            logger.error(e.getStackTrace()[0].getMethodName() + ":" + e.getMessage());
        } catch (Exception e) {
            logger.error(e.getStackTrace()[0].getMethodName() + ":" + e.getMessage());
        }
        return "index";
    }

    /**
     * 查询城市列表
     *
     * @return
     */
    @RequestMapping(path = "city_list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getCityList() {
        List<CityTbDTO> allCity = null;
        try {
            allCity = cityService.getAllCity();
        } catch (Exception e) {
            logger.error(e.getStackTrace()[0].getMethodName() + ":" + e.getMessage());
        }
        return JSON.toJSONString(allCity);
    }

    /**
     * @param cityId
     * @return
     */
    @RequestMapping(path = "movie_list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getMovieList(String cityId) {
        try {
            String json = "{}";
            MovieTbDTO[] currentCityMovie = movieService.getCurrentCityMovie(cityId);
            if (currentCityMovie == null) {
                return "0";
            }
            return JSON.toJSONString(currentCityMovie);
        } catch (Exception e) {
            logger.error("-[method:" + e.getStackTrace()[0].getMethodName() + "]:" + e.getMessage());
            return "0";
        }
    }

    /**
     * 根据当前地区的可选电影选择场次
     *
     * @param movieId
     * @param cityId
     * @return
     */
    @RequestMapping(path = "cinema_list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getCinemaByMovie(String movieId, String cityId) {
        try {
            logger.info("select movieId:" + movieId + "---" + "select cityId:" + cityId);
            CinemaTbDTO[] cinemaArray = cinemaService.getCinemaByCityAndMovie(movieId, cityId);
            if (cinemaArray == null) {
                return "0";
            }
            return JSON.toJSONString(cinemaArray);
        } catch (Exception e) {
            logger.error("-[method:" + e.getStackTrace()[0].getMethodName() + "]:" + e.getMessage());
            return "0";
        }
    }

    /**
     * 根据日期选择电影场次
     *
     * @param cityId
     * @param movieId
     * @param cinemaId
     * @param planDate
     * @return
     */
    @RequestMapping(path = "date_list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getDateByCinema(String cityId, String movieId, String cinemaId, String planDate) {
        try {
            logger.info("select movieId:" + movieId + "---" + "select cityId:" + cityId + "select planData:" + planDate);
            List<PlanTbDTO> plans = planService.getPlanByMovieAndCityAndCinemaGroupByDate(movieId, cityId, cinemaId, planDate);
            if (plans == null || plans.size() == 0) {
                return "0";
            }
            return JSON.toJSONString(plans);
        } catch (NumberFormatException e) {
            logger.error("-[method:" + e.getStackTrace()[0].getMethodName() + "]:" + e.getMessage());
            return "0";
        } catch (Exception e) {
            logger.error("-[method:" + e.getStackTrace()[0].getMethodName() + "]:" + e.getMessage());
            return "0";
        }
    }

    /**
     * 根据planId查询 plan
     *
     * @param planId
     * @return
     */
    @RequestMapping(path = "plan/{planId}.html")
    public String getPlanById(@PathVariable String planId, Model model) {
        try {
            logger.info("select planId:" + planId);
            Map<Integer, List<SelectTbDTO>> planSeat = planSeatService.getPlanSeat(planId);
            PlanTbDTO plan = planSeatService.getPlanInfo(planId);
            model.addAttribute("planSeat", planSeat);
            model.addAttribute("plan", plan);
            return "plan";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
    @RequestMapping("movie/{movieId}.html")
    public String chooseMovie( MovieTbDTO movieTbDTO,Model model){
        try {
            MovieTbDTO entityById = movieService.getEntityById(movieTbDTO);
            model.addAttribute(CommonConfig.MOVIE,entityById);
            return "movie";
        } catch (Exception e) {
            logger.error("-[method:" + e.getStackTrace()[0].getMethodName() + "]:" + e.getMessage());
        }
        return "index";
    }
}
