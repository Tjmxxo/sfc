package cc.xpress.contorller;

import cc.xpress.bean.dto.OrderTbDTO;
import cc.xpress.config.ManagePageConfig;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManageKeyConfig;
import cc.xpress.service.IOrderService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Create By Tjmxxo
 */
@Controller
public class OrderList {

    Logger logger = Logger.getLogger(Register.class);

    @Resource(name = "orderService1")
    private IOrderService orderService;

    @RequestMapping(ManageUrlConfig.ORDERLIST_URL)
    public String getOrder(HttpSession session, Model model) {
        int cinemaId = 0;
        if (session.getAttribute("user") == null) {
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY);
            return ManagePageConfig.HOME_PAGE;
        }
        Object cinemaId1 = session.getAttribute("cinemaId");
        cinemaId = Integer.parseInt(cinemaId1.toString());
        List<OrderTbDTO> allOrderByCinemaId = orderService.getAllOrderByCinemaId(cinemaId);
        if (allOrderByCinemaId == null) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "所属影院错误");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "所属影院错误");
            return ManagePageConfig.HOME_PAGE;
        }
        model.addAttribute(ManageKeyConfig.INFO_SUCCESS_KEY, "查询成功");
        model.addAttribute("orders", allOrderByCinemaId);
        return "order_list";
    }

}
