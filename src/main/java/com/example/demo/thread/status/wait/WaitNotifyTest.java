package com.example.demo.thread.status.wait;

/**
 * @Author: hanDa
 * @Date: 2020/10/9 14:57
 * @Version:1.0
 * @Description:
 */
public class WaitNotifyTest {
    public static void main(String[] args) {
        WaitObject waitObject = new WaitObject();
        new Thread(()-> waitObject.method_2()).start();
        new Thread(()-> waitObject.method_1()).start();
    }
}