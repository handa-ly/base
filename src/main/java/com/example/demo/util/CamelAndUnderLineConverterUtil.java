package com.example.demo.util;/**
 * @Author handa
 * Description:
 * Date: Created in 13:48 2020/7/28
 * Modified By:
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: hanDa
 * @Date: 2020/7/28 13:48
 * @Version:1.0
 * @Description:
 */
public class CamelAndUnderLineConverterUtil {
    private static final Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})
     */
    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    private static final Pattern humpPattern = Pattern.compile("[A-Z]");


    /**
     * 驼峰转下划线,效率比上面高
     */
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String lowerFirst(String name){
        char[] chars = name.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
       String ss = "\r|ss\ns";
        System.out.println(ss.replaceAll("\r|\n",""));
    }
}