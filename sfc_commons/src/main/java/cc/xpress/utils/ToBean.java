package cc.xpress.utils;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ToBean {
    public <T> T toBean(Class<T> clazz, HttpServletRequest req) throws FileUploadException, IOException {
        String json = "{}";
        if (ServletFileUpload.isMultipartContent(req)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            FileItemIterator ite = upload.getItemIterator(req);
            while (ite.hasNext()) {
                FileItemStream item = ite.next();
                InputStream in = item.openStream();
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = Streams.asString(in, "UTF-8");
                    System.out.println(name);
                    if (Objects.equals(name, "token")) {
                        System.out.println(name);
                        System.out.println(value);
                        System.out.println(req.getSession().getAttribute("token"));
                    } else {
                        System.out.println(1);
                        json = ToJson.toJson(json, name, value);
                    }
                } else {
                    if (in.available() != 0) {
                        String filename = item.getName();
                        if (filename != null) {
                            filename = FilenameUtils.getName(filename);
                        }
                        json = ToJson.toJson(json, item.getFieldName(), filename);
                        String path = req.getServletContext().getRealPath("/") + "img/" + filename;
                        Streams.copy(in, new FileOutputStream(path), true);
                    }
                }
            }
        }
        return new Gson().fromJson(json, clazz);
    }
}
