package cc.xpress.contorller;

import cc.xpress.bean.vo.MovieVo;
import cc.xpress.config.ManageKeyConfig;
import cc.xpress.config.ManagePageConfig;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManagerConfig;
import cc.xpress.service.IUserCountService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Create By Tjmxxo
 */
@Controller
public class MovieCount {

    Logger logger = Logger.getLogger(Login.class);

    @Resource(name = "userCountService1")
    private IUserCountService userCount;

    @RequestMapping(ManageUrlConfig.MOVIECOUNT_URL)
    public String getMovieCount(HttpSession session, Model model) {
        int cinemaId = 0;
        if (session.getAttribute(ManageKeyConfig.USER_KEY) == null) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY);
            return ManagePageConfig.HOME_PAGE;
        }
        Object cinemaId1 = session.getAttribute(ManageKeyConfig.CINEMAID_KEY);
        cinemaId = Integer.parseInt(cinemaId1.toString());
        List<MovieVo> movieCount = userCount.getMovieCount(cinemaId);
        if (movieCount == null) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, ManagerConfig.CINEMAID_WRONG);
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + ManagerConfig.CINEMAID_WRONG);
            return ManagePageConfig.HOME_PAGE;
        }
        model.addAttribute(ManageKeyConfig.MOVIE_KEY, movieCount);
        model.addAttribute(ManageKeyConfig.INFO_SUCCESS_KEY, ManagerConfig.SEARCH_SUCCESS);
        logger.info(ManageKeyConfig.INFO_SUCCESS_KEY + ManagerConfig.SEARCH_SUCCESS);
        return ManagePageConfig.MOVIE_COUNT_LIST_PAGE;
    }
}
