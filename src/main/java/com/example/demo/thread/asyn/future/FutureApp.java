package com.example.demo.thread.asyn.future;

import java.util.concurrent.*;

/**
 * @Author: hanDa
 * @Date: 2020/10/13 18:25
 * @Version:1.0
 * @Description:
 */
public class FutureApp {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(10,20,10,TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(16),Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
    ArchiveSearcher archiveSearcher = target -> target;

    void search(String name) throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> {
            System.out.println("callable执行");
            Thread.sleep(1000);
            System.out.println("callable执行完成");
            return archiveSearcher.search(name);
        };
        Future<String> future = executor.submit(callable);
//        Thread t = new Thread(new UncaughtExceptionHandler(){});
        System.out.println("其他事件");
        System.out.println(future.get());
//        future.
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureApp futureApp = new FutureApp();
        futureApp.search("测试");
    }
}
