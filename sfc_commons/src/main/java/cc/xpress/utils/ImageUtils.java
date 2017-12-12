package cc.xpress.utils;

import cc.xpress.config.CommonNotice;
import cc.xpress.config.QiNiuConfig;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

public class ImageUtils {
    private static Auth auth = Auth.create(QiNiuConfig.ACCESSKEY, QiNiuConfig.SECRETKEY);
    private static String upToken = auth.uploadToken(QiNiuConfig.BUCKET);
    //构造一个带指定Zone对象的配置类
    private static Configuration cfg = new Configuration(Zone.zone0());
    private static UploadManager uploadManager = new UploadManager(cfg);
    private static BucketManager bucketManager = new BucketManager(auth, cfg);

    /**
     * 服务器路径上传图片到云
     *
     * @param localFilePath
     */
    public static String upload(String localFilePath) {
        //...生成上传凭证，然后准备上传
        //上传地址
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = TinyUtils.getUniFileName();
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);
            if (null != putRet.hash) {
                return key;
            }
            return null;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }

    /**
     * 通过云上的文件名获取文件链接
     *
     * @param key
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String findURL(String key) throws UnsupportedEncodingException {
        return String.format("%s/%s", QiNiuConfig.DOMAINOFBUCKET, URLEncoder.encode(key, "utf-8"));
    }

    public static String findKey(String url) throws UnsupportedEncodingException {
        return URLDecoder.decode(url.replace(QiNiuConfig.DOMAINOFBUCKET, ""), "utf-8");
    }

    /**
     * 通过文件在云上的文件名删除
     *
     * @param list
     * @return
     */
    //单次批量请求的文件数量不得超过1000
    public static void delete(List<String> list) {
        if (null == list) {
            throw new NullPointerException(CommonNotice.FILE_NOT_EXISTS);
        }
        String[] keyList = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            try {
                keyList[i] = findURL(list.get(i));
            } catch (UnsupportedEncodingException e) {
                System.err.println(e.getMessage());
            }
        }
        try {
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(QiNiuConfig.BUCKET, keyList);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < keyList.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key = keyList[i];
                System.out.print(key + "\t");
                if (status.code == 200) {
                    System.out.println("delete success");
                } else {
                    System.out.println(status.data.error);
                }
            }
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        } catch (NullPointerException ex2) {
            System.err.println(ex2.toString());
        }
    }

    public static void deleteOne(String url) {
        try {
            bucketManager.delete(QiNiuConfig.BUCKET, findKey(url));
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
        }
    }
}