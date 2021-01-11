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
public class LockObjectTest {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1000,10000,50, TimeUnit.SECONDS,new ArrayBlockingQueue(1000));
        CurrentObject currentObject = new CurrentObject();
        CurrentObject1 currentObject1 = new CurrentObject1(currentObject);
        currentObject.setCurrentObject1(currentObject1);
        for (int i=0;i<1;i++) {
            threadPool.execute(() -> currentObject.getAmount("currentObject"));
            threadPool.execute(() -> currentObject1.getAmount("currentObject1"));
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