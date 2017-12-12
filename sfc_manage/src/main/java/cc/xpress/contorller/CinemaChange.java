package cc.xpress.contorller;

import cc.xpress.bean.dto.CinemaTbDTO;
import cc.xpress.config.ManageKeyConfig;
import cc.xpress.config.ManagePageConfig;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManagerConfig;
import cc.xpress.service.ICinemaChangeImpl;
import cc.xpress.utils.ToBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Create By Tjmxxo
 */
@Controller
public class CinemaChange {

    Logger logger = Logger.getLogger(CinemaList.class);

    @Resource(name = "CinemaChange1")
    private ICinemaChangeImpl cinemaService;

    @RequestMapping(ManageUrlConfig.CINEMACHANGE_URL)
    public String cinemaChange(HttpSession session ,CinemaTbDTO cinema, Model model) {
        int cinemaId = 0;
        if (session.getAttribute(ManageKeyConfig.USER_KEY) == null) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY);
            return ManageUrlConfig.REDIRECT_URL+ManageUrlConfig.CINEMASHOW_URL;
        }
        Object cinemaId1 = session.getAttribute(ManageKeyConfig.CINEMAID_KEY);
        cinemaId = Integer.parseInt(cinemaId1.toString());
        try {
            cinemaService.cinemaChange(cinema, cinemaId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, ManagerConfig.CHANGE_FAIL);
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + ManagerConfig.CINEMA_NOT_EXISTS);
            return ManageUrlConfig.REDIRECT_URL+ManageUrlConfig.CINEMASHOW_URL;
        }
        model.addAttribute(ManageKeyConfig.INFO_SUCCESS_KEY, ManagerConfig.CHANGE_SUCCESS);
        return ManageUrlConfig.REDIRECT_URL+ManageUrlConfig.CINEMASHOW_URL;
    }

    @RequestMapping(ManageUrlConfig.CINEMASHOW_URL)
    public String cinemaShow(HttpSession session, Model model) {
        int cinemaId = 0;
        if (session.getAttribute("user") == null) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY);
            return ManagePageConfig.HOME_PAGE;
        }
        Object cinemaId1 = session.getAttribute(ManageKeyConfig.CINEMAID_KEY);
        cinemaId = Integer.parseInt(cinemaId1.toString());
        try {
            CinemaTbDTO cinemaTbDTO = cinemaService.cinemaDetail(cinemaId);
            model.addAttribute(ManageKeyConfig.CINEMA_KEY, cinemaTbDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + ManagerConfig.CINEMA_NOT_EXISTS);
            return ManagePageConfig.HOME_PAGE;
        }
        return ManagePageConfig.CINEMACHANGE_PAGE;
    }

}
