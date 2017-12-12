package cc.xpress.bean.vo;

/**
 * @Create By Tjmxxo
 */
public class OnlineUserVo {
    private String sessionId;
    private String ip;
    private String firstTime;

    public OnlineUserVo() {
        super();

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
