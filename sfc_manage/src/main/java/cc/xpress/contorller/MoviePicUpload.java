package cc.xpress.contorller;

import cc.xpress.bean.dto.MovieTbDTO;
import cc.xpress.bean.vo.MovieVo;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManagerConfig;
import cc.xpress.service.IMovieService;
import cc.xpress.utils.DeleteFileUtils;
import cc.xpress.utils.ImageUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 图片上传
 */
@Controller
@RequestMapping(ManageUrlConfig.MOVIELIST_URL)
public class MoviePicUpload {

    Logger logger = org.apache.log4j.Logger.getLogger(MoviePicUpload.class);

    @Resource(name = "movieService1")
    private IMovieService movieService;

    @RequestMapping(params = "method=upload")
    @ResponseBody
    public String uploads(@RequestParam("file") MultipartFile sortPicImg, @RequestParam("uid") String uid, HttpServletRequest request) throws UnsupportedEncodingException {
        logger.info("movieId:" + uid);
        List<String> paths = null;
        try {
            paths = PicUpload.upload(request,sortPicImg,uid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(paths==null){
            throw new NullPointerException("上传失败");
        }
        Integer movieId;
        JSONObject json = new JSONObject();
        //保存
        try {
            MovieTbDTO movieTbDTO = movieService.getMovie(uid);
            movieTbDTO.setMoviePoster(paths.get(1));
            movieId = (Integer) movieService.saveMovie(movieTbDTO);
            //上传云成功删除服务器的存放图片
            DeleteFileUtils.deleteFile(paths.get(0));
            logger.info("编号为" + movieId + "影院更换相片");
            logger.info(paths.get(0));
            json.put("msg", "success");
            json.put("filePath", paths.get(0));
        } catch (NullPointerException e1) {
            logger.error(e1.getMessage());
            json.put("msg", "error");
        } catch (NumberFormatException e2) {
            logger.error(e2.getMessage());
            ImageUtils.deleteOne(paths.get(1));
            json.put("msg", "error");
        } catch (Exception e3) {
            logger.error(e3.getMessage());
            json.put("msg", "error");
            //logger.info("json="+json.toJSONString());
        }
        return json.toJSONString();
    }
}

