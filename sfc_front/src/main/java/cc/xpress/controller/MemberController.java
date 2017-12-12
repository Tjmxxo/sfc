package cc.xpress.controller;

import cc.xpress.bean.dto.OrderTbDTO;
import cc.xpress.bean.dto.UserTbDTO;
import cc.xpress.config.CommonConfig;
import cc.xpress.config.CommonNotice;
import cc.xpress.config.FrontUserConfig;
import cc.xpress.service.IFrontUserService;
import cc.xpress.service.IOrderService;
import cc.xpress.utils.ToJson;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-01 10:11
 * @modified By:
 */
@Controller
@RequestMapping("/member")
public class MemberController {
    Logger logger = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getMethodName());
    @Resource(name = "frontUserService")
    IFrontUserService frontUserService;
    @Resource(name = "orderService")
    IOrderService orderService;

    @RequestMapping(path = "modify_pass", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String modifyPassword(String userPassword, String newPassword) {
        String json = "{}";
        try {
            org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                throw new NullPointerException(CommonNotice.USER_NOT_LOGIN_IN);
            }
            UserTbDTO userTbDTO = (UserTbDTO) session.getAttribute(FrontUserConfig.USER_BEAN);
            frontUserService.modifyUserPassword(userTbDTO, newPassword, userPassword);
            return ToJson.toJson(json, CommonConfig.STATUS, "0");
        } catch (NullPointerException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ToJson.toJson(json, CommonConfig.ERROR, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ToJson.toJson(json, CommonConfig.ERROR, CommonNotice.MODIFY_FAILED);
        }
    }

    /**
     * 查询订单
     *
     * @param tag
     * @param model
     * @return
     */
    @RequestMapping(path = "order")
    public String getUserOrder(String tag, Model model) {
        try {
            org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                throw new NullPointerException(CommonNotice.USER_NOT_LOGIN_IN);
            }
            UserTbDTO userTbDTO = (UserTbDTO) session.getAttribute(FrontUserConfig.USER_BEAN);
            List<OrderTbDTO> userOrder = orderService.getUserOrder(userTbDTO, tag);
            model.addAttribute("userOrder", userOrder);
            model.addAttribute("os", tag);
            return "order";
        } catch (NullPointerException|InvalidSessionException|IllegalAccessException e) {
           logger.error(e);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return "index";
    }

    @RequestMapping(path = "account")
    public String getModifyPassword() {
        return "modify_password";
    }
}
