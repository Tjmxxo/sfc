package cc.xpress.utils;

public class TinyUtils {
    /**
     * 文件路径获取文件名
     *
     * @param path
     */

    public static String getFileName(String path) {
        if (null != path) {
            if (path.indexOf("/") != -1) {
                path = path.substring(path.lastIndexOf("/") + 1);
            } else if (path.indexOf("\\") != -1) {
                path = path.substring(path.lastIndexOf("\\") + 1);
            }
        }
        return path;
    }

    public static String slashToBack(String path) {
        if (null != path && path.indexOf("/") != -1) {
            path = path.substring(path.indexOf("/") + 1).replace("/", "\\");
            return path;
        }
        return path;
    }

    public static String backToSlash(String path) {
        if (null != path && path.indexOf("\\") != -1) {
            path = path.substring(path.indexOf("\\") + 1).replace("\\", "/");
            return path;
        }
        return path;
    }

    /**
     * 生成一个唯一文件名
     *
     * @return
     */
    public static String getUniFileName() {
        return MyUtils.getTime() + CommonUtils.getCode(7);
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */

}
