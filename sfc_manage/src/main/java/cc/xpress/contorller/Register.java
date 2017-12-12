package cc.xpress.contorller;

import cc.xpress.bean.dto.CinemaTbDTO;
import cc.xpress.bean.dto.UserTbDTO;
import cc.xpress.config.ManageKeyConfig;
import cc.xpress.config.ManagePageConfig;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManagerConfig;
import cc.xpress.service.ICinemaService;
import cc.xpress.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Create By Tjmxxo
 */
@Controller
public class Register {

    Logger logger = Logger.getLogger(Register.class);

    @Resource(name = "userService1")
    private IUserService userService;

    @Resource(name = "cinemaService1")
    private ICinemaService cinemaService;

    @RequestMapping(ManageUrlConfig.REGISTER_URL)
    public String userRegister(UserTbDTO user, int cinemaId, Model model) {
        if (user == null || userService.registerRe(user.getUserAccount())) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, ManagerConfig.ERROR_MESSAGE_REGISTERRE);
            logger.error(user.getUserAccount() + ManagerConfig.ERROR_MESSAGE_REGISTERRE);
            return ManagePageConfig.LOGIN_PAGE;
        }

        if (cinemaId == 0) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, ManagerConfig.REGISTER_CINEMAID_ERROR);
            logger.error(user.getUserAccount() + ManagerConfig.REGISTER_CINEMAID_ERROR);
            return ManagePageConfig.LOGIN_PAGE;
        }
        try {
            userService.userRegister(user, cinemaId);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            logger.error("用户注册失败");
            model.addAttribute("error_message", "用户注册失败");
        }
        return ManagePageConfig.LOGIN_PAGE;
    }

    @RequestMapping("preregister")
    public String goToRegister(Model model){
        List<CinemaTbDTO> list = cinemaService.getAllCinemaList("0");
        model.addAttribute("cinames",list);
        return ManageUrlConfig.REGISTER_URL;
    }
}
