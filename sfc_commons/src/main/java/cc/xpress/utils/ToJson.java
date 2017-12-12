package cc.xpress.utils;

public class ToJson {
    /**
     * 生成json
     *
     * @param json
     * @param key
     * @param value
     * @return
     */
    public static String toJson(String json, String key, String value) {
        if (value == null || value.length() == 0) {
            return json;
        }
        String split;
        if (json.length() <= 2)
            split = "";
        else
            split = ",";
        String str = split + "\"" + key + "\"" + ":" + "\"" + value + "\"";
        return json.substring(0, json.length() - 1) + str + "}";
    }
}