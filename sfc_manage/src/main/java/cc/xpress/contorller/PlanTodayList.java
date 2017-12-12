package cc.xpress.contorller;

import cc.xpress.config.ManageKeyConfig;
import cc.xpress.config.ManagePageConfig;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManagerConfig;
import cc.xpress.service.IPlanService;
import org.apache.log4j.Logger;
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
@RequestMapping(ManageUrlConfig.PLAN_CONTROLLER_URL)
public class PlanTodayList {
    Logger logger = Logger.getLogger(Register.class);
    @Resource(name = "planService1")
    private IPlanService planService;

    @RequestMapping(ManageUrlConfig.PLANTODAYLIST_URL)
    public String planTodayList(HttpSession session, Model model) {
        int cinemaId = 0;
        if (session.getAttribute("user") == null) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY);
            return ManagePageConfig.HOME_PAGE;
        }
        Object cinemaId1 = session.getAttribute("cinemaId");
        cinemaId = Integer.parseInt(cinemaId1.toString());
        try {
            model.addAttribute("plan", planService.getTodayPlanList(cinemaId));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "plan_list";
    }

    @RequestMapping(ManageUrlConfig.PLANALLLIST_URL)
    public String planAllList(HttpSession session, Model model) {
        int cinemaId = 0;
        if (session.getAttribute("cinemaId") == null) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY);
            return ManagePageConfig.HOME_PAGE;
        }
        Object cinemaId1 = session.getAttribute("cinemaId");
        cinemaId = Integer.parseInt(cinemaId1.toString());
        model.addAttribute("plan", planService.getAllPlanList(cinemaId));
        return "plan_list";
    }
}
