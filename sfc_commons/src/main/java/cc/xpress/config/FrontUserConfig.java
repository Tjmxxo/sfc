package cc.xpress.config;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-29 21:36
 * @modified By:
 */
public class FrontUserConfig {
    /**
     * 验证码宽度
     */
    public static final int CODE_LENGTH = 4;
    /**
     * 短信验证码储存到session的key值
     */
    public static final String SMS_CODE = "sms";
    /**
     * 创建角色的默认id
     */
    public static final int DEFAULT_ROLE_ID = 1;
    /**
     * 用户登陆储存session中的用户key值
     */
    public static final String USER_BEAN = "userBean";
}
