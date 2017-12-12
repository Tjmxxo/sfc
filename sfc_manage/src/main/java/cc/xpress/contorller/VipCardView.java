package cc.xpress.contorller;

import cc.xpress.config.ManageKeyConfig;
import cc.xpress.config.ManagePageConfig;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.service.IVipService;
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
public class VipCardView {

    Logger logger = Logger.getLogger(CinemaList.class);

    @Resource(name = "vipService1")
    private IVipService vipService;

    /**
     * 查看所有vip会员卡信息
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(ManageUrlConfig.VIPVIEW_URL)
    public String getAllVipCard(HttpSession session, Model model) {
        int cinemaId = 0;
        if (session.getAttribute(ManageKeyConfig.USER_KEY) == null) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY);
            return ManagePageConfig.HOME_PAGE;
        }
        Object cinemaId1 = session.getAttribute(ManageKeyConfig.CINEMAID_KEY);
        cinemaId = Integer.parseInt(cinemaId1.toString());
        model.addAttribute(ManageKeyConfig.VIP_KEY, vipService.getAllVip(cinemaId));
        return ManagePageConfig.VIP_LIST_PAGE;
    }
}
