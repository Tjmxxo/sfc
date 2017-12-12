package cc.xpress.contorller;

import cc.xpress.bean.dto.CinemaTbDTO;
import cc.xpress.bean.vo.CinemaVo;
import cc.xpress.config.ManageUrlConfig;
import cc.xpress.config.ManagerConfig;
import cc.xpress.service.ICinemaService;
import cc.xpress.utils.DeleteFileUtils;
import cc.xpress.utils.ImageUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
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
@RequestMapping(ManageUrlConfig.CINEMA_CONTROLLER_URL)
public class CinemaPicUpload {

    Logger logger = org.apache.log4j.Logger.getLogger(CinemaPicUpload.class);

    @Resource(name = "cinemaService1")
    private ICinemaService cinemaService;

    @RequestMapping(params = "method=upload")
    @ResponseBody
    public String uploads(@RequestParam("file") MultipartFile sortPicImg, @RequestParam("uid") String uid, HttpServletRequest request){
        logger.info("cinemaId:" + uid);
        List<String> paths = null;
        try {
            paths = PicUpload.upload(request,sortPicImg,uid);
        } catch (IOException e) {
            DeleteFileUtils.deleteFile(paths.get(0));
            ImageUtils.deleteOne(paths.get(1));
            logger.error(e.getMessage());
        }
        if(paths==null){
            throw new NullPointerException("上传失败");
        }
        Integer cinemaId;
        JSONObject json = new JSONObject();
        //保存
        try {
            CinemaTbDTO cinemaTbDTO = cinemaService.getCinema(uid);
            cinemaTbDTO.setCinemaImg(paths.get(1));
            CinemaVo cinemaVo = new CinemaVo();
            cinemaVo.setCinemaTbDTO(cinemaTbDTO);
            cinemaVo.setCityId(cinemaTbDTO.getCityTbDTO().getCityId().toString());
            logger.info("cityId" + cinemaTbDTO.getCityTbDTO().getCityId());
            cinemaId = (Integer) cinemaService.saveCinema(cinemaVo);
            //上传云成功删除服务器的存放图片
            DeleteFileUtils.deleteFile(paths.get(0));
            logger.info("编号为" + cinemaId + "影院更换相片");
            logger.info(paths.get(0));
            json.put("msg", "success");
            json.put("filePath", paths.get(0));
        } catch (NullPointerException e1) {
            logger.error(e1.getMessage());
            json.put("msg", "error");
            return json.toJSONString();
        } catch (NumberFormatException e2) {
            logger.error(e2.getMessage());
            json.put("msg", "error");
            return json.toJSONString();
        } catch (Exception e3) {
            logger.error(e3.getMessage());
            json.put("msg", "error");
            return json.toJSONString();
            //logger.info("json="+json.toJSONString());
        }
        return json.toJSONString();
    }

//    @RequestMapping(params = "method=delete")
//    @ResponseBody
//    private String deletePic(String cinemaId){
//        logger.info("cinemaId:" + cinemaId);
//        if (null == cinemaId) {
//            throw new NullPointerException("该账号不存在");
//        }
//        try {
//            CinemaTbDTO cinemaTbDTO = cinemaService.getCinema(cinemaId);
//            cinemaTbDTO.setCinemaImg("");
//            CinemaVo cinemaVo = new CinemaVo();
//            cinemaVo.setCinemaTbDTO(cinemaTbDTO);
//            cinemaVo.setCityId(cinemaTbDTO.getCityTbDTO().getCityId().toString());
//            Integer id = (Integer) cinemaService.saveCinema(cinemaVo);
//            logger.info("编号为"+id+"影院已删除");
//            return "编号为"+id+"影院已删除";
//        } catch (NullPointerException|NumberFormatException e1) {
//            logger.error(e1.getMessage());
//        }
//        return "操作失败";
//    }
}

