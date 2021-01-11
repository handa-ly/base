package com.example.demo.thread.status.yield;

/**
 * @Author: hanDa
 * @Date: 2020/10/9 11:36
 * @Version:1.0
 * @Description:
 */
public class Yield2 extends Thread{
    @Override
    public void run() {
        System.out.println("yield2 运行.");
    }
}