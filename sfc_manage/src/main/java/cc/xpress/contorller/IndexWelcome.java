package cc.xpress.contorller;

import cc.xpress.config.ManagePageConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Create By Tjmxxo
 */
@Controller
public class IndexWelcome {

    @RequestMapping("/404.html")
    public String indexPagereturn(){
        return ManagePageConfig.LOGIN_PAGE;
    }
}
