package cc.xpress.contorller;

import cc.xpress.config.ManageKeyConfig;
import cc.xpress.config.ManagePageConfig;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManagerConfig;
import cc.xpress.service.IHallService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Create By Tjmxxo
 */
@Controller
public class HallView {

    Logger logger = Logger.getLogger(Login.class);

    @Resource(name = "hallService1")
    private IHallService hallService;

    @RequestMapping(ManageUrlConfig.HALLVIEW_URL)
    public String hallView(Model model, HttpSession session) {
        int cinemaId = 0;
        if (session.getAttribute(ManageKeyConfig.USER_KEY) == null) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY);
            return ManagePageConfig.LOGIN_PAGE;
        }
        Object cinemaId1 = session.getAttribute(ManageKeyConfig.CINEMAID_KEY);
        cinemaId = Integer.parseInt(cinemaId1.toString());
        try {
            model.addAttribute(ManageKeyConfig.HALL_KEY, hallService.getAllHallTb(cinemaId));
            return ManagePageConfig.HALL_LIST_PAGE;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ManagePageConfig.HOME_PAGE;
    }

    @RequestMapping(ManageUrlConfig.HALLVIEWURGENCY_URL)
    public String hallView1(Model model, HttpSession session) {
        int cinemaId = 0;
        if (session.getAttribute(ManageKeyConfig.USER_KEY) == null) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY);
            return ManagePageConfig.LOGIN_PAGE;
        }
        Object cinemaId1 = session.getAttribute(ManageKeyConfig.CINEMAID_KEY);
        cinemaId = Integer.parseInt(cinemaId1.toString());
        try {
            model.addAttribute(ManageKeyConfig.HALL_KEY, hallService.getAllHallTb(cinemaId));
            return "urgency_hall";
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ManagePageConfig.HOME_PAGE;
    }
}
