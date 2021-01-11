package com.example.demo.thread.pool.test;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author handa
 * Description:
 * Date: Created in 20:52 2020/8/24
 * Modified By:
 */
@Service
public class ThreadTest1 implements Runnable {
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
    private BlockingQueue<List<String>> consumeBlockingQueue;

    public static void main(String[] args) {
        ThreadTest1 threadTest1 = new ThreadTest1();
        ArrayBlockingQueue<List<String>> arrayBlockingQueue = new ArrayBlockingQueue<List<String>>(100);
        threadTest1.setConsumeBlockingQueue(arrayBlockingQueue);
        // 消费数据
        while (true){
            new Thread(threadTest1).start();
        }
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            fixedThreadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "======start deal wrkWork ,current consumeBlockingQueue size is ");
                while (true) {
                }
            });
        }
            /*String ss = null;
            System.out.println(ss.toLowerCase());*/

    }

    public BlockingQueue<List<String>> getConsumeBlockingQueue() {
        return consumeBlockingQueue;
    }

    public void setConsumeBlockingQueue(BlockingQueue<List<String>> consumeBlockingQueue) {
        this.consumeBlockingQueue = consumeBlockingQueue;
    }
}