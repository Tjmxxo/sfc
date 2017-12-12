package cc.xpress.contorller;

import cc.xpress.bean.dto.HallTbDTO;
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
public class HallAdd {

    Logger logger = Logger.getLogger(Login.class);

    @Resource(name = "hallService1")
    private IHallService hallService;

    @RequestMapping(ManageUrlConfig.HALLADD_URL)
    public String addHall(HallTbDTO hall, HttpSession session, Model model) {
        int cinemaId = 0;
        if (session.getAttribute("user") == null || hall == null ||hall.getHallMaxRow() == 0 || hall.getHallMaxCol() == 0) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY);
            return ManagePageConfig.LOGIN_PAGE;
        }
        Object cinemaId1 = session.getAttribute(ManageKeyConfig.CINEMAID_KEY);
        cinemaId = Integer.parseInt(cinemaId1.toString());
        try {
            String s = hallService.saveHall(hall, cinemaId);
            if (s == null){
                model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, ManagerConfig.CINEMAID_WRONG);
                logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + ManagerConfig.CINEMAID_WRONG);
                return ManagePageConfig.WELCOME_PAGE;
            }
        } catch (Exception e) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, ManagerConfig.CINEMAID_WRONG);
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + ManagerConfig.CINEMAID_WRONG+e.getMessage());
            return ManagePageConfig.WELCOME_PAGE;
        }
        return ManagePageConfig.HALL_ADD_PAGE;
    }
}
