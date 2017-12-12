package cc.xpress.contorller;

import cc.xpress.bean.vo.OnlineUserVo;
import cc.xpress.config.ManageUrlConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * @Create By Tjmxxo
 */
@Controller
public class OnlineUserCount {

    @RequestMapping(ManageUrlConfig.ONLINECOUNT_URL)
    public String onlineUserCount(HttpSession session, Model model) {
        ServletContext servletContext = session.getServletContext();
        ArrayList<OnlineUserVo> userList = (ArrayList<OnlineUserVo>) servletContext.getAttribute("userList");
        System.out.println(userList.size());
        model.addAttribute("user", userList);
        return "online_user";
    }
}
