package com.example.demo.io.type.nio;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: hanDa
 * @Date: 2021/1/27 11:46
 * @Version:1.0
 * @Description:
 */
public class PlainOioServer {
    public void server(int port) throws IOException {
        //开启socket服务器，并监听端口
        final ServerSocket socket = new ServerSocket(port);
        try {
            for (;;){
                final Socket clientSocket = socket.accept();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("accepted connection from "+clientSocket);
                //创建新线程处理请求
                new Thread(()->{
                    try (InputStream in = clientSocket.getInputStream()){
                        byte[] b = new byte[1024];
                        int len = 0;
                        while ((len = in.read(b))>0){
                            System.out.println(new String(b,0,len));
                            /*if(socket.isClosed()){
                                break;
                            }*/
                        }
                        System.out.println("连接结束！");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        PlainOioServer plainOioServer = new PlainOioServer();
        plainOioServer.server(5555);
    }
}