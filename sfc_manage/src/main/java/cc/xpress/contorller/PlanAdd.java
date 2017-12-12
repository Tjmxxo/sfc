package cc.xpress.contorller;

import cc.xpress.bean.dto.HallTbDTO;
import cc.xpress.bean.dto.MovieTbDTO;
import cc.xpress.config.ManagePageConfig;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManageKeyConfig;
import cc.xpress.service.IHallService;
import cc.xpress.service.IMovieService;
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
public class PlanAdd {

    Logger logger = Logger.getLogger(Register.class);

    @Resource(name = "movieService1")
    private IMovieService movieService;

    @Resource(name = "hallService1")
    private IHallService hallService;

    @RequestMapping(ManageUrlConfig.PLANADD_URL)
    public String planAdd(HttpSession session, Model model) {
        int cinemaId = 0;
        if (session.getAttribute("user") == null) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY);
            return ManagePageConfig.HOME_PAGE;
        }
        Object cinemaId1 = session.getAttribute("cinemaId");
        cinemaId = Integer.parseInt(cinemaId1.toString());
        List<MovieTbDTO> allMovieByCinemaId = movieService.getAllMovieByCinemaId(cinemaId);
        if (allMovieByCinemaId == null) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "影院输入错误");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "影院输入错误");
            return ManagePageConfig.HOME_PAGE;
        }
        List<HallTbDTO> allHallTb = hallService.getAllHallTb(cinemaId);
        if (allMovieByCinemaId == null) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "影院输入错误");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "影院输入错误");
            return ManagePageConfig.HOME_PAGE;
        }
        model.addAttribute("movie", allMovieByCinemaId);
        model.addAttribute("hall", allHallTb);
        return "plan_add";
    }
}
