package com.example.demo.concurrent;

/**
 * @Author: hanDa
 * @Date: 2021/3/10 10:21
 * @Version:1.0
 * @Description:
 */
public class ExampleTest {
    private int count = 0;
    public synchronized void increment() {
        count ++;
    }

    public synchronized void decrement(){
        count --;
    }
}