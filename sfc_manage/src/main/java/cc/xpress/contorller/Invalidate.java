package cc.xpress.contorller;

import cc.xpress.config.ManagePageConfig;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Create By Tjmxxo
 */
@Controller
public class Invalidate {

    @RequestMapping("/toLogin")
    public String execute(HttpSession session){
        SecurityUtils.getSubject().logout();
        session.invalidate();
        return ManagePageConfig.HOME_PAGE;
    }
}
