package com.example.demo.io;/**
 * @Author handa
 * Description:
 * Date: Created in 9:53 2020/1/6
 * Modified By:
 */

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @description:
 * @author: handa
 * @time: 2020/1/6 9:53
 */
public class Client {
    public void test() throws UnknownHostException, IOException, InterruptedException {
        String msg = "11";
        byte[] byteMsg = msg.getBytes();

        Socket socket = new Socket("localhost", 8888);
        OutputStream out = socket.getOutputStream();
        BufferedOutputStream bw = new BufferedOutputStream(out);
        int i = 0;
        while (true) {
            bw.write(byteMsg);
            Thread.sleep(2 * 1000);
            bw.flush();
            while (i == 100) {
                return;
            }

        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.test();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
