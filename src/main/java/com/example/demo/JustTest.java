package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.mozilla.intl.chardet.*;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hanDa
 * @Date: 2021/2/1 17:44
 * @Version:1.0
 * @Description:
 */
public class JustTest {
    public static boolean found = false;
    public static void main(String[] args) throws Exception {
//        test(new String[]{"C:/Users/handa_ly/Desktop/getTest.txt"});
//        test(new String[]{"C:/Users/handa_ly/Desktop/顯示為0 KOMCA1- (1).020"});
//        test(new String[]{"http://180.166.161.210:18075/#/payment-detail?autopayNo=ab12343&payType=SOC"});
//        System.out.println(String.format("%011d","1"));
        System.out.println(StringUtils.contains(null,null));
    }
    static char getChar(byte[] b, int off) {
        return (char) ((b[off + 1] & 0xFF) +
                (b[off] << 8));
    }
   /* static void checkFileCharSetTest(){
        String filePath = "C:\\Users\\handa_ly\\Desktop\\getTest.txt";
        String fileEncode=EncodingDetect.getJavaEncode(filePath);
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
                     byte[] b = new byte[100];
                     while (fileInputStream.read(b) > -1){
                         String s = new String(b);
                         System.out.println(s);
                         System.out.println("==============");
                     }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static void test(String[] var0) throws Exception {
        if (var0.length != 1 && var0.length != 2) {
            System.out.println("Usage: HtmlCharsetDetector <url> [<languageHint>]");
            System.out.println("");
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

                for(int var10 = 0; var10 < var9.length; ++var10) {
                    System.out.println("Probable Charset = " + var9[var10]);
                }
            }

        }
    }

    private void test1(){
        nsUTF8Verifier n = new nsUTF8Verifier();
//        n.charset()
    }



}