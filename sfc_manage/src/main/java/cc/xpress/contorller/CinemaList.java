package cc.xpress.contorller;

import cc.xpress.bean.dto.CinemaTbDTO;
import cc.xpress.bean.vo.CinemaVo;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManagerConfig;
import cc.xpress.service.ICinemaService;
import cc.xpress.service.ICityService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(ManageUrlConfig.CINEMA_CONTROLLER_URL)
public class CinemaList{

    @Resource(name = "cinemaService1")
    private ICinemaService cinemaService;

    @Resource(name = "cityService1")
    private ICityService cityService;

    Logger logger = Logger.getLogger(CinemaList.class);

    @RequestMapping(ManageUrlConfig.CINEMALIST_URL)
    public String cinemaAllList(String cityId, Model model) {
        try {
            model.addAttribute("cinemas", cinemaService.getAllCinemaList(cityId));
            model.addAttribute("cities",cityService.getAllCity());
            logger.info("查询编号为" + cityId + "城市的影院，成功");
            return "cinema/cinema_list";
        } catch (NullPointerException e1) {
            logger.error(e1.getMessage());
        } catch (NumberFormatException e2) {
            logger.error(e2.getMessage());
        }
        return "cinema/cinema_list";
    }

    @RequestMapping(ManageUrlConfig.CINEMAADD_URL)
    @ResponseBody
    public String cinemaAddOrUpdate(CinemaTbDTO cinemaTbDTO, String cityId, Model model) {
        CinemaVo cinemaVo = new CinemaVo(cinemaTbDTO,cityId);
        String flag;
        Integer cinemaId;
        try {
            if (cinemaVo == null||cinemaTbDTO.getCinemaName() == null) {
                throw new NullPointerException("影院信息全为空或者影院名为空");
            }
            cinemaId = cinemaTbDTO.getCinemaId();
            if (cinemaId == null) {
                flag = "新增电影院";
            } else {
                flag = "修改电影院";
            }
            String id=cinemaService.saveCinema(cinemaVo).toString();
            model.addAttribute("cinemas", cinemaService.getAllCinemaList(cityId));
            logger.info(flag+"编号为"+id);
            return "success";
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
        }
        return "fail";
    }

    @RequestMapping(ManageUrlConfig.CINEMAPREUPDATE_URL)
    public String cinemaPreUpdate(String cinemaId,Model model) {
        CinemaTbDTO cinemaTbDTO;
        try {
            if (cinemaId == null){
                throw new NullPointerException("修改电影院编号为空");
            }
            cinemaTbDTO = cinemaService.getCinema(cinemaId);
            logger.info("用户准备修改");
            model.addAttribute("cinema",cinemaTbDTO);
            return "cinema/cinema_add";
        } catch (NumberFormatException e1) {
            logger.error(e1.getMessage());
        } catch (IllegalArgumentException e2) {
            logger.error(e2.getMessage());
        } catch (NullPointerException e3){
            logger.error(e3.getMessage());
        }
        return "cinema/cinema_list";
    }

    @RequestMapping(ManageUrlConfig.CINEMAPREDELETE_URL)
    @ResponseBody
    public String cinemaDelete(String[] cinemaIds,Model model){
        List<Integer> integers;
        try {
            if(cinemaIds == null){
                throw new NullPointerException("删除电影的编号为空");
            }
            integers = cinemaService.deleteCinema(cinemaIds);
            model.addAttribute("cinemas", cinemaService.getAllCinemaList(integers.get(0).toString()));
            for (int i=1;i<integers.size();i++) {
                logger.info("编号为"+integers.get(i)+"的影院删除成功");
            }
            return "success";
        } catch (NumberFormatException e1) {
            logger.error(e1.getMessage());
        } catch (IllegalArgumentException e2){
            logger.error(e2.getMessage());
        }
        return "fail";
    }
}