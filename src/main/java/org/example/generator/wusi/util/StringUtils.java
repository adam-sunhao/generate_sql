package org.example.generator.wusi.util;

import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author AdamSun
 * @date 2020/5/24 9:31
 */
public class StringUtils {

    private static String regex = "\\#\\{(?<name>\\w+)\\}";
    private static Pattern pattern = Pattern.compile(regex);

    /**
     * 格式化prop中的值数据库名
     *
     * @param prop
     */
    public static void format(Properties prop) {
        if (prop == null) {
            return;
        }
        for (String key : prop.stringPropertyNames()) {
            String content = prop.getProperty(key);
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                String name = matcher.group("name");
                if ("tableName".equals(name)) {
                    break;
                }
                prop.setProperty(key, format(content, prop.getProperty(name)));
            }
        }
    }

    /**
     * 将字符中的模板格式化
     *
     * @param str
     * @param value
     * @return
     */
    public static String format(String str, String value) {
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, value == null ? "" : value);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String processTemplate(String template, Map<String, Object> params) {
        Matcher matcher = pattern.matcher(template);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String param = matcher.group("name");
            Object value = params.get(param);
            matcher.appendReplacement(sb, value == null ? "" : value.toString());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static boolean isBlank(String str) {
        return "".equals(trim2Empty(str));
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String trim2Empty(String str) {
        return str == null ? "" : str.trim();
    }
}
