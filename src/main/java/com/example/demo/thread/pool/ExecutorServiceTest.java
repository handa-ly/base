package com.example.demo.thread.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @ClassName: ExecutorServiceTest
 * @Description: TODO
 * @Author: handa
 * @Date: 2020/4/21 12:33
 */


public class ExecutorServiceTest {
    static int i =0;
    private static ExecutorService poolThreadFactory;

    public static void main(String[] args) {


        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("缓存").build();
        poolThreadFactory = new ThreadPoolExecutor(2, 20,
                1000, TimeUnit.MILLISECONDS, new SynchronousQueue<>(),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        for (int i =0;i<30;i++){
            poolThreadFactory.submit(new ThreadTask());
        }
    }

    private static class ThreadTask implements Runnable {
        @Override
        public void run() {
            System.out.println("执行中。。。。"+i);
            //执行逻辑
            System.out.println("执行wan。。。。"+i++);
        }
    }
}
