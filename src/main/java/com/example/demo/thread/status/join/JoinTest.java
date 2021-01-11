package com.example.demo.thread.status.join;

import com.example.demo.thread.status.join.Join01;

/**
 * @Author: hanDa
 * @Date: 2020/10/9 11:13
 * @Version:1.0
 * @Description:
 */
public class JoinTest {
    public static void main(String[] args) {
        Thread t = new Thread(new Join01(),"thread t");
        Thread t1 = new Thread(new Join01(),"thread t1");
        t1.start();
        t.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("current Thread");
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + (i + 1));
        }
    }
}
