package com.example.demo.byteTrans;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.EncodingUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @Author: hanDa
 * @Date: 2021/1/14 16:57
 * @Version:1.0
 * @Description:
 */
public class UtfTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
       /* String s = "知";
        char c =0x6210;
        System.out.println((int)s.charAt(0));
        System.out.println(c);*/
        System.out.println(binStr("知乎", StandardCharsets.UTF_8));
    }

    /**
     * 传入字符和相应的编码，返回计算机使用的二进制编码
     * 只为演示，未优化方法，未处理 RuntimeException
     */
    static String binStr(String str, Charset encoding)
            throws UnsupportedEncodingException {
        var bytes = ArrayUtils.toObject(str.getBytes(encoding));
        if (Byte.toUnsignedInt(bytes[0]) == 0xfe
                && Byte.toUnsignedInt(bytes[1]) == 0xff) {
            bytes = Arrays.copyOfRange(bytes, 2, bytes.length);
        }
        return  Arrays.stream(bytes).collect(
                StringBuilder::new,
                (sb, b) -> {
                    if (sb.length() > 0) {
                        sb.append(' ');
                    }
                    var s = Integer.toBinaryString(Byte.toUnsignedInt(b));
                    sb.append(StringUtils.leftPad(s, 8, '0'));
                },
                (sb1, sb2) -> { sb1.append(' ').append(sb2); }
        ).toString();
    }

}
