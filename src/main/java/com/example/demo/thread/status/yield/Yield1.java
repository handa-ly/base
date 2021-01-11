package com.example.demo.thread.status.yield;

/**
 * @Author: hanDa
 * @Date: 2020/10/9 11:35
 * @Version:1.0
 * @Description:
 */
public class Yield1 extends Thread {
    @Override
    public void run() {
            System.out.println("yield1 挂起前运行.");
            //让当前线程暂停
            yield();
            System.out.println("yield1 挂起后运行");
    }
}