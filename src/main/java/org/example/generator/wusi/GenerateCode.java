package org.example.generator.wusi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author AdamSun
 * @date 2020/5/24 13:18
 */
public class GenerateCode {

    /**
     * 生成基础ModelAndView访问代码
     *
     * @param methodName
     * @param desc
     * @param author
     * @return
     */
    public static String generateModelAndView(String methodName, String desc, String author) {
        StringBuilder sb = new StringBuilder();
        sb.append("/**").append("\r\n")
                .append(" * @desc ").append(desc).append("\r\n")
                .append(" * @author ").append(author).append("\r\n")
                .append(" * @date ").append(getCurFormatDate()).append("\r\n")
                .append(" * @return ").append("\r\n")
                .append(" */").append("\r\n");
        sb.append("@RequestMapping(\"").append(methodName).append("\")\r\n")
                .append("public ModelAndView ").append(methodName).append("()").append("{\r\n")
                .append("    return createMav(\"").append(getViewName(methodName)).append("\");\r\n")
                .append("}");
        return sb.toString();
    }

    /**
     * 从变量中取出符合的值，判断是否为空
     *
     * @param variableName 变量名
     * @return
     */
    public static String generateCondition(String variableName, String... params) {
        StringBuilder sb = new StringBuilder();
        for (String param : params) {
            sb.append("String ").append(param)
                    .append(" = ").append(variableName).append(".getStr(\"")
                    .append(param).append("\");\r\n");
            sb.append("if(StringUtils.isNotBlank(").append(param)
                    .append(")) {\r\n");
            sb.append("}").append("\r\n\r\n");
        }
        return sb.toString();
    }

    /**
     * 将sql语句拼接成需要的格式
     *
     * @param variableName 变量名
     * @return
     */
    public static String sql2Java(String variableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n").append("\r\n").append("\r\n").append("\r\n");
        sb.append("--------------------------------------------------\r\n");
        sb.append("SqlBuilder ").append(variableName).append(" = ")
                .append("new SqlBuilder();").append("\r\n");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //跳过空行
            if ("".equals(trim2Empty(line))) {
                continue;
            }
            //结束标志
            if (trim2Empty(line).startsWith(";")
                    || trim2Empty(line).startsWith("；")) {
                scanner.close();
                break;
            }
            sb.append(variableName).append(".append(\"")
                    .append(line).append("\");\r\n");
        }
        return sb.toString();
    }

    /**
     * 将sql拼接语句转为普通sql
     *
     * @return
     */
    public static String java2Sql() {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n").append("\r\n").append("\r\n").append("\r\n");
        sb.append("--------------------------------------------------\r\n");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //跳过空行
            if ("".equals(trim2Empty(line))) {
                continue;
            }
            //结束标志
            if (trim2Empty(line).startsWith(";")
                    || trim2Empty(line).startsWith("；")) {
                scanner.close();
                break;
            }
            int start = line.indexOf(".append(\"");
            int end = line.lastIndexOf("\")");
            if (start < 0 || end < 0) {
                continue;
            }
            sb.append(line.substring(start + 9, end)).append("\r\n");
        }
        return sb.toString();
    }

    private static String trim2Empty(String line) {
        if (line == null) {
            return "";
        }
        return line.trim();
    }

    /**
     * 获取当前格式化后的日期
     *
     * @return
     */
    private static String getCurFormatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 ah:mm:ss");
        Date now = new Date();
        return sdf.format(now);
    }

    /**
     * 根据方法名获取视图名
     *
     * @param methodName
     * @return
     */
    private static String getViewName(String methodName) {
        char[] methodNameArr = methodName.substring(5).toCharArray();
        methodNameArr[0] = Character.toLowerCase(methodNameArr[0]);
        return new String(methodNameArr);
    }

}
