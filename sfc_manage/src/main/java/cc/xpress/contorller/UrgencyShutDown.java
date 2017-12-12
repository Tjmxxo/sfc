package cc.xpress.contorller;

import cc.xpress.config.ManageKeyConfig;
import cc.xpress.config.ManagePageConfig;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManagerConfig;
import cc.xpress.service.IUrgencyService;
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
@RequestMapping("/urgency")
public class UrgencyShutDown {

    Logger logger = Logger.getLogger(Register.class);

    @Resource(name = "urgencyService1")
    private IUrgencyService urgencyService;

    @RequestMapping(ManageUrlConfig.CINEMA_URL)
    public String urgencyCinemaShutdown(HttpSession session, Model model, String timel,String mainpassword) {
        long times = 0;
        if (timel != null) {
            times = Long.parseLong(timel) * 60L * 60L * 24L * 1000L;
        } else {
            times = 99999999999L;
        }
        int cinemaId = 0;
        if (!mainpassword.equals("12345678")){
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY,"错误的密码!!");
            return ManagePageConfig.URGENCY_CINEMA_PAGE;
        }
        if (session.getAttribute("user") == null) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY);
            return ManagePageConfig.HOME_PAGE;
        }
        Object cinemaId1 = session.getAttribute("cinemaId");
        cinemaId = Integer.parseInt(cinemaId1.toString());
        String s = urgencyService.planStateChange(cinemaId, times, 0);
        if (s == null) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "座位信息输入错误");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "座位信息输入错误");
            return ManagePageConfig.HOME_PAGE;
        }
        logger.info(ManageKeyConfig.INFO_SUCCESS_KEY + "影院状态修改成功");
        model.addAttribute(ManageKeyConfig.INFO_SUCCESS_KEY, "影院状态修改成功");
        return ManagePageConfig.WELCOME_PAGE;
    }

    @RequestMapping(ManageUrlConfig.HALL_URL)
    public String urgencyHallShutdown(int hallId, Model model, String time) {
        long times = 0;
        if (time != null) {
            times = Long.parseLong(time) * 60L * 60L * 24L * 1000L;
        } else {
            times = 99999999999L;
        }
        String s = urgencyService.planStateChange(hallId, times, 1);
        if (s == null) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "座位信息输入错误");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "座位信息输入错误");
            return ManagePageConfig.HOME_PAGE;
        }
        logger.info(ManageKeyConfig.INFO_SUCCESS_KEY + "影厅状态修改成功");
        model.addAttribute(ManageKeyConfig.INFO_SUCCESS_KEY, "影厅状态修改成功");
        return ManageUrlConfig.REDIRECT_URL + ManageUrlConfig.HALLVIEWURGENCY_URL;
    }

    @RequestMapping(ManageUrlConfig.SEAT_URL)
    public String urgencySeatShutdown(int seatId, Model model) {
        if (seatId == 0) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "座位信息输入错误");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "座位信息输入错误");
            return ManagePageConfig.HOME_PAGE;
        }
        String s = urgencyService.seatStateChange(seatId);
        if (s == null) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "座位信息输入错误");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "座位信息输入错误");
            return ManagePageConfig.HOME_PAGE;
        }
        logger.info(ManageKeyConfig.INFO_SUCCESS_KEY + "座位状态修改成功");
        model.addAttribute(ManageKeyConfig.INFO_SUCCESS_KEY, "座位状态修改成功");
        return ManageUrlConfig.REDIRECT_URL + ManageUrlConfig.HALLVIEWURGENCY_URL;
    }
}
