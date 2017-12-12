package cc.xpress.contorller;

import cc.xpress.bean.dto.SeatTbDTO;
import cc.xpress.config.ManageKeyConfig;
import cc.xpress.config.ManagePageConfig;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManagerConfig;
import cc.xpress.service.ISeatService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @Create By Tjmxxo
 */
@Controller
public class SeatSave {

    Logger logger = Logger.getLogger(Register.class);

    @Resource(name = "seatService1")
    private ISeatService seatService;

    @RequestMapping(ManageUrlConfig.SEATCHANGE_URL)
    public String seatChange(int hallId, Model model) {
        if (hallId == 0) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "座位信息输入错误");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "座位信息输入错误");
            return ManagePageConfig.HOME_PAGE;
        }
        List<SeatTbDTO> allSeatByHallId = seatService.getAllSeatByHallId(hallId);
        if (allSeatByHallId == null) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "座位信息输入错误");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "座位信息输入错误");
            return ManagePageConfig.HOME_PAGE;
        }
        logger.info(ManageKeyConfig.INFO_SUCCESS_KEY + "座位查询成功");
        model.addAttribute("seat", allSeatByHallId);
        model.addAttribute("hallId", hallId);
        return "seat_list";
    }

    @RequestMapping(ManageUrlConfig.SEATSAVE_URL)
    public String seatSave(SeatTbDTO seatTbDTO, Model model,int hallId) {
        Serializable serializable = seatService.saveSeat(seatTbDTO,hallId);
        if (serializable == null) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "座位信息输入错误");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "座位信息输入错误");
            return ManagePageConfig.HOME_PAGE;
        }
        logger.info(ManageKeyConfig.INFO_SUCCESS_KEY + "座位信息修改成功");
        model.addAttribute(ManageKeyConfig.INFO_SUCCESS_KEY, "座位信息修改成功");
        return "redirect:/"+ManageUrlConfig.HALLVIEW_URL;
    }

    @RequestMapping(ManageUrlConfig.SEATCHANGEURGENCY_URL)
    public String seatChanges(int hallId, Model model) {
        if (hallId == 0) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "座位信息输入错误");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "座位信息输入错误");
            return ManagePageConfig.HOME_PAGE;
        }
        List<SeatTbDTO> allSeatByHallId = seatService.getAllSeatByHallId(hallId);
        if (allSeatByHallId == null) {
            model.addAttribute(ManageKeyConfig.ERROR_MESSAGE_KEY, "座位信息输入错误");
            logger.error(ManageKeyConfig.ERROR_MESSAGE_KEY + "座位信息输入错误");
            return ManagePageConfig.HOME_PAGE;
        }
        logger.info(ManageKeyConfig.INFO_SUCCESS_KEY + "座位查询成功");
        model.addAttribute("seat", allSeatByHallId);
        return "urgency_seat";
    }
}
