package com.example.demo.thread;

import lombok.extern.slf4j.Slf4j;

/**
    *@ClassName: Xttblog
    *@Description: 可重入锁测试
    *@Author: handa
    *@Date: 2020/4/20 17:45
    */
@Slf4j
public class Xttblog extends SuperXttblog {
    public static void main(String[] args) {
        Thread.currentThread().setName("Thread1");
        Xttblog child = new Xttblog();
        child.doSomething();
        new Thread(()-> System.out.println("222222222222"+Thread.currentThread().getName())).start();
        try {
            Thread.sleep(100l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void doSomething() {
        System.out.println("child.doSomething()" + Thread.currentThread().getName());
        doAnotherThing(); // 调用自己类中其他的synchronized方法
    }

    private synchronized void doAnotherThing() {
        super.doSomething(); // 调用父类的synchronized方法
        System.out.println("child.doAnotherThing()" + Thread.currentThread().getName());
    }
}

class SuperXttblog {
    public synchronized void doSomething() {
        System.out.println("father.doSomething()" + Thread.currentThread().getName());
    }
}
