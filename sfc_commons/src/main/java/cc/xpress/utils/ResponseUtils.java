package cc.xpress.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-10-27 22:05
 * @modified By:
 */
public class ResponseUtils {
    /**
     * 拦截后的响应
     *
     * @param msg
     * @param response
     * @throws IOException
     */
    public static void interceptedResponse(String msg, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html; charset=UTF-8");
        writer.print(msg);
    }
}
