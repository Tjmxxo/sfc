package cc.xpress.contorller;

import cc.xpress.bean.dto.MovieTbDTO;
import cc.xpress.bean.vo.PageBean;
import cc.xpress.config.ManagePageConfig;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManagerConfig;
import cc.xpress.service.IMovieService;
import cc.xpress.utils.CommonUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(ManageUrlConfig.MOVIELIST_URL)
public class MovieList {

    Logger logger = Logger.getLogger(MovieList.class);

    @Resource(name = "movieService1")
    private IMovieService movieService;

    @RequestMapping(ManageUrlConfig.MOVIEALLLIST)
    public String movieAllLIst(String pageNo, Model model) {
        if (!CommonUtils.isConvertedToNumber(pageNo) || Integer.parseInt(pageNo) < 1) {
            throw new NumberFormatException("页码不为整");
        }
        try {
            if(null == pageNo){
                pageNo = "1";
            }
            PageBean<MovieTbDTO> from = movieService.getPageBean("from", MovieTbDTO.class, pageNo, Integer.parseInt(ManagerConfig.PAGE_SIZE));
            model.addAttribute("movies", from.getList());
            logger.info("查询全部电影");
            return "movie/movie_list";
        } catch (NumberFormatException e1) {
            logger.error(e1.getMessage());
            return "redirect:/" + ManageUrlConfig.MOVIELIST_URL + ManageUrlConfig.MOVIEALLLIST + "?pageNo=1";
        } catch (IllegalArgumentException e2) {
            logger.error(e2.getMessage());
            return ManagePageConfig.HOME_PAGE;
        }
    }


}
