package cc.xpress.contorller;

import cc.xpress.config.ManageKeyConfig;
import cc.xpress.config.ManagePageConfig;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManagerConfig;
import cc.xpress.service.IVipService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Create By Tjmxxo
 */
@Controller
public class VipCardAdd {

    Logger logger = Logger.getLogger(CinemaList.class);

    @Resource(name = "vipService1")
    private IVipService vipService;

    @RequestMapping(path = ManageUrlConfig.VIPADD_URL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String registerLoginAccountRe(HttpSession session, int num) {
        if (num == 0 || num > 10000) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + ManagerConfig.VIPCARDADD_WRONGNUM);
            return ManagePageConfig.HOME_PAGE;
        }
        int cinemaId = 0;
        if (session.getAttribute("user") == null) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY);
            return ManagePageConfig.HOME_PAGE;
        }
        Object cinemaId1 = session.getAttribute("cinemaId");
        cinemaId = Integer.parseInt(cinemaId1.toString());
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            vipService.addVip(num, cinemaId);
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + ManagerConfig.VIPCARDADD_SUCCESS);
            map.put(ManageKeyConfig.AJAX_MESSAGE_KEY, ManagerConfig.VIPCARDADD_SUCCESS);
        } catch (Exception e) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + ManagerConfig.VIPCARDADD_SUCCESS);
            map.put(ManageKeyConfig.AJAX_MESSAGE_KEY, ManagerConfig.VIPCARDADD_SUCCESS);
        }
        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
