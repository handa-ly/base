package com.example.demo;

import com.example.demo.pojo.quartz.QuartzJobLog;
import com.example.demo.utils.LocalCommonMethodUtils;
import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @Author: hanDa
 * @Date: 2021/2/1 17:44
 * @Version:1.0
 * @Description:
 */
public class JustTest {
    public static boolean found = false;
    /*public static void main(String[] args) throws Exception {
        System.out.println(0.2f);

    }*/

    /***
    * @Description:  测试 LocalCommonMethodUtils.distinctByKey 方法  以确定stream新特性
    * @Param: []
    * @return: java.util.List<com.example.demo.pojo.quartz.QuartzJobLog>
    * @Author: hanDa
    * @Date: 2021/3/16 14:40
    */
    public static List<QuartzJobLog> testDistinctByKey(){
        List<QuartzJobLog> quartzJobLogList = new ArrayList<>();
        for (int i =0;i<3;i++){
            QuartzJobLog quartzJobLog = new QuartzJobLog();
            quartzJobLog.setHost("2222");
            quartzJobLog.setPort("99999");
            quartzJobLogList.add(quartzJobLog);
        }
        return quartzJobLogList.stream().filter(LocalCommonMethodUtils.distinctByKey((QuartzJobLog::getHost))).collect(Collectors.toList());
    }


    /***
    * @Description:  校验文件编码
    * @Param: [var0]
    * @return: void
    * @Author: hanDa
    * @Date: 2021/3/16 14:33
    */
    /*public static void test(String[] var0) throws Exception {
        if (var0.length != 1 && var0.length != 2) {
            System.out.println("Usage: HtmlCharsetDetector <url> [<languageHint>]");
            System.out.println();
            System.out.println("Where <url> is http://...");
            System.out.println("For optional <languageHint>. Use following...");
            System.out.println("\t\t1 => Japanese");
            System.out.println("\t\t2 => Chinese");
            System.out.println("\t\t3 => Simplified Chinese");
            System.out.println("\t\t4 => Traditional Chinese");
            System.out.println("\t\t5 => Korean");
            System.out.println("\t\t6 => Dont know (default)");
        } else {
            int var1 = var0.length == 2 ? Integer.parseInt(var0[1]) : 0;
            nsDetector var2 = new nsDetector(var1);
            var2.Init(var11 -> {
                HtmlCharsetDetector.found = true;
                System.out.println("CHARSET = " + var11);
            });
//            URL var3 = new URL(var0[0]);
            FileInputStream fileInputStream = new FileInputStream(var0[0]);
            BufferedInputStream var4 = new BufferedInputStream(fileInputStream);
            byte[] var5 = new byte[1024];
            boolean var7 = false;
            boolean var8 = true;

            int var6;
            while((var6 = var4.read(var5, 0, var5.length)) != -1) {
                if (var8) {
                    var8 = var2.isAscii(var5, var6);
                }

                if (!var8 && !var7) {
                    var7 = var2.DoIt(var5, var6, false);
                }
            }

            var2.DataEnd();
            if (var8) {
                System.out.println("CHARSET = ASCII");
                found = true;
            }

            if (!found) {
                String[] var9 = var2.getProbableCharsets();

                for (String s : var9) {
                    System.out.println("Probable Charset = " + s);
                }
            }

        }
    }*/

    /**
    * @Description:         1.使用之前请调用getAllDetectableCharsets()检查是否满足要求，中文仅有{gb18030, big5,utf-*}import com.ibm.icu.text.CharsetDetector;
     *                      import com.ibm.icu.text.CharsetMatch;
    *                       2.测试编码
     * @Param: [fileName]
    * @return: java.nio.charset.Charset
    * @Author: hanDa
    * @Date: 2021/3/16 14:42
    */
    static Charset getWhiteList(String fileName) {
        return getCharset(fileName);
    }

    public static Charset getCharset(String fileName) {
        if (fileName == null) {
            return null;
        }

        InputStreamReader isr;
        try (FileInputStream fis = new FileInputStream(fileName)){
            // markSupported
            BufferedInputStream bis = new BufferedInputStream(fis);
            CharsetMatch charsetMatch = new CharsetDetector().setText(bis).detect();
            if (charsetMatch != null) {
                System.out.println("Open '" + fileName + " ' with charset: " + charsetMatch.getName());
                return  Charset.forName(charsetMatch.getName());
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
    public static void test(String ss){

    }
    public static void test(){

    }
    public static Integer test(HashMap s){
         return 0;
    }
    public static Integer test(Map s){
         return 0;
    }


    public static void main(String[] args) {
        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
        List<String> gi = givenList.stream().collect(Collectors.toUnmodifiableList());
        assertThatThrownBy(() -> gi.add("foo"))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}