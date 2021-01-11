package com.example.demo.thread.status.yield;

/**
 * @Author: hanDa
 * @Date: 2020/10/9 11:37
 * @Version:1.0
 * @Description:
 */
public class Yield3 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("第三个线程第" + (i + 1) + "次运行.");
            //让当前线程暂停
            yield();
        }
    }
}