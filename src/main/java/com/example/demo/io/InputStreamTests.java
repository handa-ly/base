package com.example.demo.io;


import java.io.*;
import java.net.Socket;

/**
 * @description:
 * @author: handa
 * @time: 2020/1/6 9:40
 */
public class InputStreamTests {
    public static void main(String[] args) {
        String logPath = "D:/log/11/517_test.log";
        String outPath = "D:/log/574_test.log";
        //读取路径下面的文件
        //读取指定路径下面的文件
        InputStream in = null;
        OutputStream outputStream = null;
        try {
//            in = new FileInputStream(logPath);
            in = new Socket().getInputStream();
            outputStream = new BufferedOutputStream(new FileOutputStream(outPath));
            //创建存放文件内容的数组
            byte[] buff = new byte[1024];
            //所读取的内容使用n来接收
            int n;
            //当没有读取完时,继续读取,循环
            while ((n = in.read(buff)) != -1) {
                //将字节数组的数据全部写入到输出流中
                outputStream.write(buff, 0, n);
            }
            //强制将缓存区的数据进行输出
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关流
            try {
                outputStream.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
