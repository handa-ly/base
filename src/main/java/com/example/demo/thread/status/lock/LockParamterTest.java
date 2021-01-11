package com.example.demo.thread.status.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: hanDa
 * @Date: 2020/10/9 15:27
 * @Version:1.0
 * @Description: 锁定当前对象造成的死锁
 */
public class LockParamterTest {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1000,10000,50, TimeUnit.SECONDS,new ArrayBlockingQueue(1000));
        CurrentObject currentObject = new CurrentObject();
        for (int i=0;i<10000;i++){
            threadPool.execute(() -> currentObject.getA("A"));
            threadPool.execute(() -> currentObject.getB("B"));
        }
        threadPool.shutdown();
        while (!threadPool.isTerminated()){
            if (threadPool.isTerminated()){
                System.out.println("finished");
                break;
            }
        }
    }
}