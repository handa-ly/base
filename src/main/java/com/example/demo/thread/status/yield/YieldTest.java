package com.example.demo.thread.status.yield;

/**
 * @Author: hanDa
 * @Date: 2020/10/9 11:38
 * @Version:1.0
 * @Description:
 */
public class YieldTest {
    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            Yield1 yield1 = new Yield1();
            Yield2 yield2 = new Yield2();
            Yield3 yield3 = new Yield3();
            yield1.start();
            yield2.start();
            try {
                yield1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("+++++++++++++++++++++++++");
//        yield3.start();
        }
    }


}