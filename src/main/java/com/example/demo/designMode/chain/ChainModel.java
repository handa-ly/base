package com.example.demo.designMode.chain;

import java.nio.charset.Charset;

/**
 * @Author: hanDa
 * @Date: 2020/12/25 17:40
 * @Version:1.0
 * @Description:
 */
public class ChainModel {
    public static byte[] strToByteArray(String str) {
        if (str == null) {
            return null;
        }
        byte[] byteArray = str.getBytes();
        return byteArray;
    }

    public static void main(String[] args) {
//        new String(new byte[1,2],Charset.forName(""));

    }

}