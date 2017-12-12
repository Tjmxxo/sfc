package cc.xpress.utils;

import com.aliyun.api.gateway.demo.util.HttpUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MyUtils {
    /**
     * 获取项目图片储存路径
     *
     * @param servlet 当前servlet
     * @param param   图片储存子路径
     * @return 项目图片储存路径
     */
    public static <T extends HttpServlet> String getProjectPath(T servlet, String param) {
        return getProjectPath(servlet.getServletContext(), param);
    }

    /**
     * 通过ServletContext获取XML配置文件
     *
     * @param ctx
     * @param param
     * @return
     */
    public static String getProjectPath(ServletContext ctx, String param) {
        String imgPath = (String) ctx.getInitParameter("imgPath");
        String childPath = (String) ctx.getInitParameter(param);
        if (imgPath == null || childPath == null)
            return null;
        else
            return imgPath + childPath;
    }

    /**
     * 根据FileItem的文件完成路径获取文件名
     *
     * @param item
     * @return
     */
    public static String getFileName(FileItem item) {
        return item.getName();
    }

    /**
     * 获取文件名类型长度
     *
     * @return
     */
    public static int getFileTypeLength(FileItem item) {
        String fileName = getFileName(item);
        return fileName.length() - fileName.indexOf(".");
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmssSS");
        return df.format(date);
    }

    /**
     * 根据当前时间和截断长度获取文件名
     *
     * @param cutLength
     * @return
     * @throws
     */
    public static String CreateFileName(FileItem item, int cutLength) throws IllegalArgumentException {
        String fileName = getFileName(item);
        if (cutLength < getFileTypeLength(item))
            throw new IllegalArgumentException("截取长度过短");
        else if (cutLength > fileName.length())
            return getTime() + fileName;
        else
            return getTime() + fileName.substring(fileName.length() - cutLength);
    }

    /**
     * 获取当前时间生成的文件名
     *
     * @return
     * @throws IllegalArgumentException
     */
    public static String CreateFileName(FileItem item) throws IllegalArgumentException {
        return CreateFileName(item, getFileTypeLength(item));
    }

    /**
     * 获取当前时间+文件全名的文件名
     *
     * @return
     * @throws IllegalArgumentException
     */
    public static String CreateFullFileName(FileItem item) throws IllegalArgumentException {
        return CreateFileName(item, Integer.MAX_VALUE);
    }

    /**
     * 数字转换成十六进制数字
     *
     * @return
     */
    public static String createPackNo() {
        return Long.toHexString(Long.parseLong(getTime())).toString().toUpperCase();
    }

    /**
     * 生成指定位数的数字字符串
     *
     * @param length
     * @return
     */
    public static String CreateNum(int length) {
        Random rand = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append(rand.nextInt(10));
        }
        return str.toString();
    }

    /**
     * 键值对转json
     *
     * @param json
     * @param key
     * @param value
     * @return
     */
    public static String toJson(String json, String key, String value) {
        //判断是否为空
        if (value == null)
            return json;
        else {
            //去首尾空格
            value = value.trim();
            //判断长度是否为0
            if (value.length() == 0)
                return json;
        }
        String str = (json.length() <= 2 ? "" : ",") + "\"" + key + "\"" + ":" + "\"" + value + "\"";
        return json.substring(0, json.length() - 1) + str + "}";
    }

    /**
     * 提取请求参数
     *
     * @param req
     * @return
     */
    public static String getReqUrl(HttpServletRequest req) {
        Map<String, String[]> params = req.getParameterMap();
        String queryString = "";
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                queryString += key + "=" + value + "&";
            }
        }
        // 去掉最后一个&
        if (queryString.length() > 1)
            queryString = queryString.substring(0, queryString.length() - 1);
        return req.getServletPath() + "?" + queryString;
    }

    /**
     * 短信发送方法
     *
     * @param appCode
     * @param paramString  json类型
     * @param recNum       目标手机号码
     * @param signName     签名名称
     * @param templateCode 模板CODE
     * @return
     */
    public static String msgSend(String appCode, String paramString, String recNum, String signName, String templateCode) {
        String host = "http://sms.market.alicloudapi.com";
        String path = "/singleSendSms";
        String method = "GET";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE" + " " + appCode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("ParamString", paramString);
        querys.put("RecNum", recNum);
        querys.put("SignName", signName);
        querys.put("TemplateCode", templateCode);
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            return EntityUtils.toString(response.getEntity()) + "11111";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\": false}";
        }
    }
}
