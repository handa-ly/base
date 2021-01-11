package com.example.demo.io;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.ServerSocket;

/**
 * @description:
 * @author: handa
 * @time: 2020/1/6 9:41
 */
public class Servers {
    public void test() throws IOException {
        ServerSocket ss = new ServerSocket(8888);
        InputStream is = null;
        try {
            is = ss.accept().getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] data = new byte[8];
        int length;
        while ((length = is.read(data)) != -1) {
            String result = new String(data);
            System.out.println(result);
            System.out.println("length:" + length);
        }
        System.out.print("success");
    }

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(2500);
        BigDecimal b = new BigDecimal(100000);
        System.out.println(a.divide(b));
        /*Servers server = new Servers();
        try {
            server.test();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
