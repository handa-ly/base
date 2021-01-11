package com.example.demo.thread.lock;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: lockObject
 * @Description: TODO
 * @Author: handa
 * @Date: 2020/4/20 9:34
 */
public class LockObject {
    ReentrantLock lock = new ReentrantLock();
    AtomicInteger i = new AtomicInteger(0);
    public void test(String path) {
        System.out.println("对象方法锁定前");
        lock.lock();
        System.out.println("对象被锁定的方法");
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        System.out.println("对象被锁定的方法释放锁");
        System.exit(0);
    }
    public void test1(AtomicInteger i) {
        System.out.println("未被对象锁定的方法执行次数："+ i.getAndIncrement());
    }
}
