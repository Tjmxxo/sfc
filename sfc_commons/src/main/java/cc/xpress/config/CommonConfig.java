package cc.xpress.config;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-01 11:00
 * @modified By:
 */
public class CommonConfig {
    /**
     * 邮箱正则表达式验证
     */
    public static final String MAIL_REGEX = "^([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\-|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$";
    /**
     * 密码正则表达验证
     */
    public static final String PASSWORD_REGEX = "^[0-9a-zA-Z.]{6,16}$";
    /**
     * 手机正则表达验证
     */
    public static final String TEL_REGEX = "^(13|14|15|17|18|)\\d{9}$";
    /**
     * 账户正则验证
     */
    public static final String ACCOUNT_REGEX = "^[a-zA-Z][a-zA-Z0-9]$";
    /**
     * 错误提示key
     */
    public static final String ERROR = "error";
    /**
     * 状态key
     */
    public static final String STATUS = "status";
    /**
     * 当前选中城市key
     */
    public static final String CURRENT_CITY = "currentCity";
    /**
     * redis分布式锁超时时间
     */
    public static final int REDIS_LOCK_TIMEOUT = 1000;
    /**
     * 发生不可预期的错误
     */
    public static final String UNEXPECTED_ERROR = "服务器发生了不可预期的错误";
    /**
     * order的key
     */
    public static final String ORDER = "order";
    /**
     *  消息队列 orderId
     */
    public static final String RABBITMQ_ORDER_ID="orderId";
    /**
     * movie key
     */
    public static final String MOVIE="movie";
}
