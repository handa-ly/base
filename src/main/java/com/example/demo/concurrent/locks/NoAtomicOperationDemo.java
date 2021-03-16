package com.example.demo.concurrent.locks;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: hanDa
 * @Date: 2021/3/10 15:21
 * @Version:1.0
 * @Description:
 */
public class NoAtomicOperationDemo {
    static long count = 0;

    public static void add() {
        count++;
    }

    public static void main(String[] args) {
        NoAtomicOperationDemo.AtomicIntShow();
    }

    /**
     * 开启多个线程进行自加加操作
     * 赘述:多个线程必须共用一把锁*
     */
    public static void AtomicIntShow() {
        NoAtomicOperationDemo noAtomicOperationDemo = new NoAtomicOperationDemo();
        /**
         * jdk中ReentrantLock
         */
//       Lock lock = new ReentrantLock();
        /**
         * 使用 AtomicInteger 自实现的锁
         */
        Lock lock = noAtomicOperationDemo.new IConcurrentLock();
        System.out.println("启动...");
        ExecutorService threadpool = Executors.newFixedThreadPool(10);

        for (int k = 0; k < 100; k++) {
            threadpool.submit(new AddThread(lock));
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("result of acumulated sum=" + count);
        threadpool.shutdown();
        System.out.println("AtomicIntShow() exit");
        return;

    }

    /**
     * 执行调用的线程
     */
    public static class AddThread implements Runnable {
        Lock lock;

        public AddThread() {
        }

        public AddThread(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int k = 0; k < 1000; k++) {
                lock.lock();
                try {
                    NoAtomicOperationDemo.add();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }
    private class IConcurrentLock implements Lock {
        //初始化为 0
        private AtomicInteger atomicInteger = new AtomicInteger(0);
        private ConcurrentLinkedQueue<Thread> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void lock() {
            if (atomicInteger.compareAndSet(0, 1)) {
                return;
            }

            //首先，将当前线程放置等待队列中
            concurrentLinkedQueue.add(Thread.currentThread());

            //自旋_死等
            while (true) {
                if (0 == atomicInteger.get()) {
                    //期望是0，是0返回true，否则加1并返回false
                    if (atomicInteger.compareAndSet(0, 1)) {
                        concurrentLinkedQueue.remove(Thread.currentThread());
                        return;
                    }
                } else {
                    //挂起当前线程
                    LockSupport.park();
                }
            }
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, @NotNull TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {
            atomicInteger.set(0);
            //唤醒等待队列的第一个线程
            Thread waiterHead = concurrentLinkedQueue.peek();
            if (null != waiterHead) {
                //唤醒线程
                LockSupport.unpark(waiterHead);
            }
        }

        @NotNull
        @Override
        public Condition newCondition() {
            return null;
        }
    }
}