package cc.xpress.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Base64;

public class EncryptUtils {
    /**
     * 对字符串md5加密(小写+字母)
     *
     * @param str 传入要加密的字符串
     * @return MD5加密后的字符串
     */
    private static String getMd5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * base64位编码
     *
     * @param str
     * @return
     */
    private static String getBase64(String str) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(str.getBytes());
        return new String(encode);
    }

    /**
     * 密码加密使用随机盐
     *
     * @param password
     * @param salt
     * @return
     */
    public static String encryptPassword(String password, String salt) {
        return getMd5(getBase64(salt) + getBase64(password));
    }

    /**
     * 加密密码使用username当盐
     *
     * @param username
     * @param password
     * @return
     */
    public static String getPassword(String username, String password) {
        return getMd5(getBase64(username) + getBase64(password));
    }
}
