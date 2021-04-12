package com.example.demo.utils;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author: hanDa
 * @Date: 2020/7/28 13:48
 * @Version:1.0
 * @Description:
 */
public class LocalCommonMethodUtils {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static final String START_SPACES_REGEX = "^\\s+";
    public static final String EMPTY_STRING = "";
    private static final String NUMBER_PATTERN = "-?[0-9]+\\.?[0-9]*";
    static final Pattern PATTERN = Pattern.compile(NUMBER_PATTERN);
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

    private static Pattern humpPattern = Pattern.compile("[A-Z]");


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

    public static String lowerFirst(String name) {
        char[] chars = name.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    /***
     * @Description: 单字符 A-Z输出为 1-26
     * @Param: [capital]
     * @return:
     * @Author: hanDa
     * @Date: 2020/8/24 10:44
     */
    public static Integer capitalToNumber(String input) {
        String reg = "[a-zA-Z]";
        StringBuffer strBuf = new StringBuffer();
        input = input.toLowerCase();
        if (null != input && !"".equals(input)) {
            for (char c : input.toCharArray()) {
                if (String.valueOf(c).matches(reg)) {
                    strBuf.append(c - 96);
                } else {
                    strBuf.append(c);
                }
            }
            return Integer.valueOf(String.valueOf(strBuf));
        } else {
            return 0;
        }
    }

    /**
     * @Description:  计算自动审核未通过数量
     * @Param: [matchedCountMap, listMatchDataDsp]
     * @return: void
     * @Author: hanDa
     * @Date: 2020/8/28 10:40
     */
    public static void getUnMatchedCountMap(Long fileBaseId,Map<Long, AtomicInteger> matchedCountMap) {
        if(fileBaseId != null){
            if(matchedCountMap.containsKey(fileBaseId)){
                AtomicInteger matchedCount = matchedCountMap.get(fileBaseId);
                matchedCount.incrementAndGet();
            }else {
                AtomicInteger matchedCount = new AtomicInteger(1);
                matchedCountMap.put(fileBaseId,matchedCount);
            }
        }
    }
    /**
     * @Description:  计算自动审核通过数量
     * @Param: [matchedCountMap, listMatchDataDsp]
     * @return: void
     * @Author: hanDa
     * @Date: 2020/8/28 10:40
     */
    public static void getMatchedCountMap(Long fileBaseId, Map<Long, AtomicInteger> matchedCountMap) {
        if(fileBaseId != null){
            if(matchedCountMap.containsKey(fileBaseId)){
                AtomicInteger matchedCount = matchedCountMap.get(fileBaseId);
                matchedCount.incrementAndGet();
            }else {
                AtomicInteger matchedCount = new AtomicInteger(1);
                matchedCountMap.put(fileBaseId,matchedCount);
            }
        }
    }



    public static BigDecimal calculateFromPercent(BigDecimal amount, BigDecimal numerator,BigDecimal denominator){
        amount = amount == null ? BigDecimal.ZERO : amount;
        numerator = numerator == null ? BigDecimal.ZERO : numerator;
        denominator = denominator == null ? BigDecimal.ZERO : denominator;
        if (denominator.signum() != 0){
            return amount.multiply(numerator).divide(numerator.add(denominator),0,RoundingMode.HALF_UP);
        }
        else {
            return amount;
        }
    }

    /**
    * @Description:  拼接workuniquekey
    * @Param: [soc, workId]
    * @return: java.lang.String
    * @Author: hanDa
    * @Date: 2020/9/17 10:18
    */
    public static String getWorkUniqueKey(Integer soc,Long workId){
        return Optional.ofNullable(soc).map(s->{
            if (workId != null){
                return StringUtils.leftPad(s + "", 3, "0").concat("-").concat(workId.toString());
            }
            return null;
        }).orElse(null);

    }

    public static String getForESWildcardQuery(String query){
        return Optional.ofNullable(query).map(s->{
            return "*".concat(query).concat("*");
        }).orElse(null);
    }



    public static String checkLengthAndIntercept(String context, int count, int length) {
        if (StringUtils.isNotBlank(context)) {
            context = String.join(";", Arrays.stream(context.split(";")).filter(Objects::nonNull).limit(count).collect(Collectors.toList()));
            while (context.length() > length){
                String oldString = context;
                context = String.join(";", Arrays.stream(oldString.split(";")).limit(--count).collect(Collectors.toList()));
                if (context.equals(oldString)){
                    break;
                }
            }
        }
        return context;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static String trimStart(String value) {
        return value.replaceFirst(START_SPACES_REGEX, EMPTY_STRING);
    }

    public static <T extends Comparable<T>> boolean isBetween(T value, T start, T end) {
        return value.compareTo(start) >= 0 && value.compareTo(end) <= 0;
    }

    public static boolean isNumber(String str) {
        Matcher isNum = PATTERN.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /***
    * @Description:  识别文件编码格式
    * @Param: [fileName]
    * @return: java.util.HashSet<java.lang.String>
    * @Author: hanDa
    * @Date: 2021/3/12 16:09
    */
    public static Charset getCharset(String fileName) {
        if (fileName == null) {
            return null;
        }

        InputStreamReader isr;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            // markSupported
            BufferedInputStream bis = new BufferedInputStream(fis);
            CharsetMatch charsetMatch = new CharsetDetector().setText(bis).detect();
            if (charsetMatch != null) {
                System.out.println("Open '" + fileName + " ' with charset: " + charsetMatch.getName());
                return Charset.forName(charsetMatch.getName());
            } else {
                isr = new InputStreamReader(bis);
                System.out.println(
                        "Open '" + fileName + " ' with charset( default, because no charset is detected by IBM.ICU4J): "
                                + isr.getEncoding());
                return Charset.forName(isr.getEncoding());
            }
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: File '" + fileName + "' is not exist.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("WARNING: IOException occured when read Whitelist.");
        }
        return null;
    }

    public static String isDate(String date)
    {
        /**
         * 判断日期格式和范围
         */
        String rexp = "([1-9])\\d{3}-((0([1-9]))|(1(0|1|2)))|(([1-9])\\d{3}((0([1-9]))|(1(0|1|2))))";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(date);
        if (mat.find()){
            return mat.group(0);
        }
        return null;

    }


    public static void main(String[] args) {
        /**
         * 输出结果
         */
        System.out.println(isDate("202010"));
        System.out.println(isDate("2020-10"));
        System.out.println(isDate("PRECLAIM_PARTNER-A_GB_2020-10"));

    }
}