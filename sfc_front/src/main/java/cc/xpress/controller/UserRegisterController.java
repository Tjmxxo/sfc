package cc.xpress.controller;

import cc.xpress.bean.dto.UserTbDTO;
import cc.xpress.config.CommonConfig;
import cc.xpress.config.FrontUserConfig;
import cc.xpress.service.IFrontUserService;
import cc.xpress.utils.CommonUtils;
import cc.xpress.utils.SmsUtils;
import com.mchange.util.DuplicateElementException;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-29 21:23
 * @modified By:
 */
@Controller
@RequestMapping("/user")
public class UserRegisterController {
    private static Logger logger = Logger.getLogger(UserRegisterController.class);
    @Autowired
    ShardedJedisPool shardedJedisPool;
    @Resource(name = "frontUserService")
    IFrontUserService frontUserService;

    /**
     * 跳转登陆页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(path = "/login")
    public String getLoginPage(HttpServletRequest request, Model model) {
        return "login";
    }

    /**
     * 跳转注册页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(path = "/register")
    public String getRegisterPage(HttpServletRequest request, Model model) {
        return "register";
    }

    /**
     * 用户注册
     *
     * @param userTbDTO
     * @param sms
     * @param httpSession
     * @param model
     * @return
     */

    @RequestMapping(path = "/user_register")
    public String userRegister(UserTbDTO userTbDTO, String sms, HttpSession httpSession, Model model) {
        try {
            logger.info(userTbDTO.getUserTel() + userTbDTO.getUserPassword() + sms);

            if (sms == null || "".equals(sms)) {
                throw new NullPointerException("验证码为空");
            }
            String code = (String) httpSession.getAttribute(FrontUserConfig.SMS_CODE);
            if (code == null || "".equals(code)) {
                throw new NullPointerException("未获取验证码");
            }
            if (!sms.equals(code)) {
                throw new IllegalArgumentException("验证码不正确");
            }
            UserTbDTO userTbDTO1 = frontUserService.userRegister(userTbDTO);
            UsernamePasswordToken token = new UsernamePasswordToken(userTbDTO.getUserTel(), userTbDTO.getUserPassword());
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            session.setAttribute(FrontUserConfig.USER_BEAN,userTbDTO1);
            subject.login(token);
            return "modify_password";
        } catch (NullPointerException | DuplicateElementException e) {
            logger.error(e.getMessage());
            model.addAttribute("error", e.getMessage());
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return "register";
    }

    @RequestMapping(path = "/user_login")
    public String user_login(UserTbDTO userTbDTO) {
        try {
            /*判断userTbDTO是否为空*/
            if (userTbDTO == null || userTbDTO.getUserTel() == null || userTbDTO.getUserPassword() == null) {
                throw new NullPointerException("用户名或密码为空");
            }
            /*生成认证Token*/
            UsernamePasswordToken token = new UsernamePasswordToken(userTbDTO.getUserTel(), userTbDTO.getUserPassword());
            Subject subject = SecurityUtils.getSubject();
            /*登陆*/
            subject.login(token);
            logger.info("user:" + userTbDTO.getUserTel() + " login success");
            Session session = subject.getSession();
            UserTbDTO userByTel = frontUserService.getUserByTel(userTbDTO);
            session.setAttribute(FrontUserConfig.USER_BEAN, userByTel);
            return "redirect:/member/order";
        } catch (NullPointerException|AuthenticationException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "login";
    }

    @RequestMapping("/login_out")
    public String userLoginOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }

    /**
     * 获取图片验证码
     *
     * @param resp
     * @param httpSession
     */
    @RequestMapping(path = "/get_code", method = RequestMethod.GET)
    public void getCode(HttpServletResponse resp, HttpSession httpSession) {
        String code = CommonUtils.getCode(FrontUserConfig.CODE_LENGTH);
        BufferedImage codeImage = CommonUtils.getCodeImage(code, 85, 45, 25);
        try {
            resp.setHeader("Cache-control", "no-cache");
            httpSession.setAttribute("code", code);
            boolean png = ImageIO.write(codeImage, "png", resp.getOutputStream());
            if (png) {
                httpSession.setAttribute("code", code.toLowerCase());
            }
            logger.info("get image Code:" + code);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 返回发送的验证码
     *
     * @param httpSession
     * @return
     */
    @RequestMapping(path = "check_code", produces = MediaType.ALL_VALUE)
    @ResponseBody()
    public String checkCode(HttpSession httpSession) {
        String code = (String) httpSession.getAttribute("code");
        logger.info("check image code:" + code);
        return code;
    }

    /**
     * 获取验证短信
     *
     * @param mobile
     * @return
     */
    @RequestMapping(path = "get_sms", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody()
    public String getSms(String mobile, HttpSession httpSession) {
        try{
            String code = CommonUtils.getCode(6);
            String result = SmsUtils.singleSms(mobile, code);
            logger.info(result);
            httpSession.setAttribute(FrontUserConfig.SMS_CODE, code);
            logger.info("get sms code:" + code);
            return "{\"success\":true}";
        }catch (Exception e){
            logger.error(e);
            return "{\"success\":false}";
        }
    }
}
