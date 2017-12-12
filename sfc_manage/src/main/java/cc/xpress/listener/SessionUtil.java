package cc.xpress.listener;

import cc.xpress.bean.vo.OnlineUserVo;

import java.util.ArrayList;

/**
 * @Create By Tjmxxo
 */
public class SessionUtil {
    public static OnlineUserVo getUserBySessionId(ArrayList<OnlineUserVo> userList, String sessionId) {
        for (OnlineUserVo user : userList) {
            if (sessionId.equals(user.getSessionId())) {
                return user;
            }
        }
        return null;
    }
}
