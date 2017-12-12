package cc.xpress.utils;

import cc.xpress.config.CommonNotice;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Random;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-10-27 20:17
 * @modified By:
 */
public class CommonUtils {
    private static final String CHAR_BUCKET = "0123456789ABCDEFHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static Random rand = new Random();

    /**
     * 创建长度为length的随机数字字母字符串
     *
     * @param length
     * @return
     */
    public static String getCode(int length) {
        char[] chars = CHAR_BUCKET.toCharArray();
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(chars[random.nextInt(chars.length)]);
        }
        return stringBuilder.toString();
    }

    /**
     * 判断是否能转换成是数字
     *
     * @param string
     * @return
     */
    public static boolean isConvertedToNumber(String string) {
        if (string == null || string.equals("")) {
            return false;
        }
        char[] chars = string.toCharArray();
        for (char ch : chars) {
            if ((int) ch < 45 || (45 < (int) ch && (int) ch < 48) || (int) ch > 57) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取注解属性的值
     *
     * @param entity
     * @param clazz
     * @return
     */
    public static Serializable getEntityValue(Object entity, Class clazz) throws IllegalAccessException, ClassCastException {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            Annotation annotation = field.getAnnotation(clazz);
            if (annotation != null) {
                field.setAccessible(true);
                Object object = field.get(entity);
                if (!(object instanceof Serializable)) {
                    throw new ClassCastException(CommonNotice.GET_ENTITY_ID_FAILED);
                }
                return (Serializable) field.get(entity);
            }
        }
        return null;
    }

    /**
     * 过滤字符串的某字符
     *
     * @param string
     * @param ch
     * @return
     */
    public static String charFilter(String string, char ch) {
        char[] chars = string.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char chs : chars) {
            if (chs != ch) {
                stringBuilder.append(chs);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 生成code验证码
     *
     * @param code
     * @return
     */
    public static BufferedImage getCodeImage(String code, int imageWidth, int imageLength, int fontSize) {
        BufferedImage image = new BufferedImage(imageWidth, imageLength, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        int codeLength = code.length();
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, imageWidth, imageLength);
        graphics.setFont(new Font("console", Font.PLAIN, fontSize));
        char[] chars = code.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            graphics.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
            graphics.drawString(chars[i] + "", i * imageWidth / codeLength + rand.nextInt(imageWidth / codeLength) / 2, imageLength - rand.nextInt(imageLength - fontSize));
            graphics.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
            graphics.drawLine(rand.nextInt(imageWidth), rand.nextInt(imageLength), rand.nextInt(imageWidth), rand.nextInt(imageLength));
        }
        return image;
    }

}
