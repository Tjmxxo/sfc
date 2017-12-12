package cc.xpress.contorller;

import cc.xpress.bean.dto.PlanTbDTO;
import cc.xpress.config.ManageKeyConfig;
import cc.xpress.config.ManagePageConfig;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManageKeyConfig;
import cc.xpress.service.IPlanTimerService;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

/**
 * @Create By Tjmxxo
 */
@Controller
public class PlanTimer {

    Logger logger = Logger.getLogger(CinemaList.class);

    @Resource(name = "planTimerServie1")
    private IPlanTimerService planTimerService;

    @RequestMapping(ManageUrlConfig.PLANSET_URL)
    public String addPlanTimer(HttpSession session, Model model, PlanTbDTO plan, String startTime, String time, int hallId, int movieId) {
        if (session == null || plan == null || startTime == null || time == null || hallId == 0) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "场次有误请重新输入");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "场次有误请重新输入");
            return ManagePageConfig.HOME_PAGE;
        }
        try {
            planTimerService.setPlanTimer(plan, startTime, time, hallId, movieId);
        } catch (ParseException e) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "场次有误请重新输入");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "场次有误请重新输入");
            return ManagePageConfig.HOME_PAGE;
        } catch (IllegalArgumentException e) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "场次有误请重新输入");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "场次有误请重新输入");
            return ManagePageConfig.HOME_PAGE;
        } catch (SchedulerException e) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "场次有误请重新输入");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "场次有误请重新输入");
            return ManagePageConfig.HOME_PAGE;
        }
        model.addAttribute(ManageKeyConfig.INFO_SUCCESS_KEY, "添加完成,场次计划已开始");
        return "plan_add";
    }

}
