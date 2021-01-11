package com.example.demo.thread.status.join;

/**
 * @Author: hanDa
 * @Date: 2020/10/9 11:16
 * @Version:1.0
 * @Description:
 */
public class Join01 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +": execute");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}