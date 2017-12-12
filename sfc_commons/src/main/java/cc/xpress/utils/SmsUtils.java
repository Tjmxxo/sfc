package cc.xpress.utils;

import com.aliyun.api.gateway.demo.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-01 9:50
 * @modified By:
 */
public class SmsUtils {
    private static String propertiesPath = "/sms.properties";
    private static Properties prop=new Properties();
    static {
        try {
            InputStream resourceAsStream = SmsUtils.class.getResourceAsStream(propertiesPath);
            InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream, "UTF-8");
            prop.load(inputStreamReader);
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String singleSms(String phoneNum, String code) {
        try {
            String host = prop.getProperty("host");
            String path = prop.getProperty("path");
            String method = prop.getProperty("method");
            String appCode = prop.getProperty("appCode");
            String signName = prop.getProperty("signName");
            String templateCode = prop.getProperty("templateCode");
            String paramString = prop.getProperty("paramString");
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Authorization", "APPCODE" + " " + appCode);
            Map<String, String> querys = new HashMap<String, String>();
            querys.put("ParamString", "{\"" + paramString + "\":\"" + code + "\"}");
            querys.put("RecNum", phoneNum);
            querys.put("SignName", signName);
            querys.put("TemplateCode", templateCode);
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"success\": false}";
    }
}
