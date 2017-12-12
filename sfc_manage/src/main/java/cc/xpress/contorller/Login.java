package cc.xpress.contorller;


import cc.xpress.bean.dto.UserTbDTO;
import cc.xpress.config.ManageKeyConfig;
import cc.xpress.config.ManagePageConfig;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManagerConfig;
import cc.xpress.service.ICinemaChangeImpl;
import cc.xpress.service.IUserService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Create By Tjmxxo
 */
@Controller
public class Login {

    Logger logger = Logger.getLogger(Login.class);
    @Resource(name = "userService1")
    private IUserService userService;

    @Resource(name = "CinemaChange1")
    private ICinemaChangeImpl cinemaService;

    @RequestMapping(ManageUrlConfig.LOGIN_URL)
    public String userLogin(UserTbDTO user, Model model, HttpSession session) {
        if (user != null) {
            try {
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserAccount(), user.getUserPassword());
                Subject subject = SecurityUtils.getSubject();
                subject.login(usernamePasswordToken);
                subject.checkPermission("insert");
                UserTbDTO userByAccount = userService.getUserByAccount(user.getUserAccount());
                session.setAttribute(ManageKeyConfig.USER_KEY, userByAccount);
                session.setAttribute(ManageKeyConfig.CINEMAID_KEY, cinemaService.getCinemaId(user));
                logger.info(ManagerConfig.LOGIN_SUCCESS);
                return ManagePageConfig.HOME_PAGE;
            } catch (AuthenticationException e) {
                logger.error(e.getMessage());
                logger.error(ManagerConfig.LOGIN_FAIL);
                model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, ManagerConfig.LOGIN_FAIL);
            } catch (Exception e) {
                logger.error(e.getMessage());
                logger.error(ManagerConfig.LOGIN_FAIL);
                model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, ManagerConfig.LOGIN_FAIL);
            }
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, ManagerConfig.LOGIN_FAIL);
        }
        return "login";
    }
}
