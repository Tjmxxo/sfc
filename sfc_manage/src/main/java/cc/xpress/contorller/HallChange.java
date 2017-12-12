package cc.xpress.contorller;

import cc.xpress.bean.dto.HallTbDTO;
import cc.xpress.config.ManageKeyConfig;
import cc.xpress.config.ManagePageConfig;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManagerConfig;
import cc.xpress.service.IHallService;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Create By Tjmxxo
 */
@Controller
public class HallChange {

    Logger logger = Logger.getLogger(Login.class);

    @Resource(name = "hallService1")
    private IHallService hallService;

    @RequestMapping(ManageUrlConfig.HALLCHANGE_URL)
    public ModelAndView hallView(Model model, HttpSession session, HallTbDTO hall) {
        int cinemaId = 0;
        if (session.getAttribute(ManageKeyConfig.USER_KEY) == null) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY);
            return new ModelAndView(ManagePageConfig.LOGIN_PAGE);
        }
        Object cinemaId1 = session.getAttribute(ManageKeyConfig.CINEMAID_KEY);
        cinemaId = Integer.parseInt(cinemaId1.toString());
        try {
            hallService.changeHall(hall, cinemaId);
            return new ModelAndView("redirect:/" + ManageUrlConfig.HALLVIEW_URL);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ModelAndView("redirect:/" + ManagePageConfig.WELCOME_PAGE);
    }
}
