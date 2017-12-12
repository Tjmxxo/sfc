package cc.xpress.Intercepter;

import cc.xpress.config.CommonConfig;
import cc.xpress.config.CommonNotice;
import cc.xpress.utils.ResponseUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-10-27 20:59
 * @modified By:
 */
public class RegexInterceptor implements HandlerInterceptor {
    /**
     * 如果参数有邮箱和密码，则对邮箱和密码进行正则表达式验证
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String userAccount = httpServletRequest.getParameter("userAccount");
        String userPassword = httpServletRequest.getParameter("userPassword");
        String userTel = httpServletRequest.getParameter("userTel");
        if (userAccount != null) {
            boolean matches = Pattern.matches(userAccount, CommonConfig.ACCOUNT_REGEX);
            if (!matches) {
                ResponseUtils.interceptedResponse(CommonNotice.ERROR_ACCOUNT_FORMAT, httpServletResponse);
                return false;
            }
        }
        if (userPassword != null) {
            boolean matches = Pattern.matches(CommonConfig.PASSWORD_REGEX, userPassword);
            if (!matches) {
                ResponseUtils.interceptedResponse(CommonNotice.ERROR_TEL_FORMAT, httpServletResponse);
                return false;
            }
        }
        if (userTel != null) {
            boolean matches = Pattern.matches(CommonConfig.PASSWORD_REGEX, userTel);
            if (!matches) {
                ResponseUtils.interceptedResponse(CommonNotice.ERROR_TEL_FORMAT, httpServletResponse);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
