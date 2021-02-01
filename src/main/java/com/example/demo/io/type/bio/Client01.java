package com.example.demo.io.type.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author: hanDa
 * @Date: 2021/1/13 11:03
 * @Version:1.0
 * @Description:
 */
public class Client01 {
    public static void main(String[] args) throws IOException {
        //创建套接字对象socket并封装ip与port
        Socket socket = new Socket("127.0.0.1", 5555);
        //根据创建的socket对象获得一个输出流
        //基于字节流
        OutputStream outputStream = socket.getOutputStream();
        //控制台输入以IO的形式发送到服务器
        System.out.println("TCP连接成功 \n请输入：");
        String str = new Scanner(System.in).nextLine();
        byte[] car = str.getBytes();
        outputStream.write(car);
        System.out.println("TCP协议的Socket发送成功");
        //刷新缓冲区
        outputStream.flush();
        //关闭连接
        socket.close();
    }
}