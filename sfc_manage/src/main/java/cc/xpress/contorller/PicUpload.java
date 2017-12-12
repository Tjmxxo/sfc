package cc.xpress.contorller;

import cc.xpress.config.QiNiuConfig;
import cc.xpress.utils.DeleteFileUtils;
import cc.xpress.utils.ImageUtils;
import cc.xpress.utils.TinyUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class PicUpload {

    public static List<String> upload(HttpServletRequest request,MultipartFile picture,String uid) throws NullPointerException ,IOException{
        if (null == uid) {
            throw new NullPointerException("该账号不存在");
        }
        //原始名获得扩展名
        String extension = FilenameUtils.getExtension(picture.getOriginalFilename());
        List<String> paths = new ArrayList<>();
        //存放在服务器的地址
//        paths.add(request.getSession().getServletContext().getResource("/") + QiNiuConfig.TEMP_PATH + TinyUtils.getUniFileName() + "." + extension);
        paths.add(request.getSession().getServletContext().getRealPath("/") + QiNiuConfig.TEMP_PATH + TinyUtils.getUniFileName() + "." + extension);

        //上传到服务器暂存
        //
        //上线时删除
        //

//        TinyUtils.backToSlash(paths.get(0)).substring(6);


        File targetFile = new File(paths.get(0));
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        picture.transferTo(targetFile);
        //云上的地址
        paths.add(ImageUtils.findURL(ImageUtils.upload(paths.get(0))));
        return paths;
    }
}
