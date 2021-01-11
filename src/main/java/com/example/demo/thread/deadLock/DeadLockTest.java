package com.example.demo.thread.deadLock;

/**
 * @Author: hanDa
 * @Date: 2020/10/14 16:35
 * @Version:1.0
 * @Description:
 */
public class DeadLockTest {
    private String s = "ss";
    private String a="aa";

     class LockS{
        public void test() throws InterruptedException {
                synchronized (s){
                    System.out.println("as执行开始");
                    Thread.sleep(1000);
                    synchronized (a){
                        System.out.println("锁住sa");
                    }
                 }
            System.out.println("as执行结束");

        }
    }
     class LockA{
        public void test() throws InterruptedException {
            synchronized (a){
                System.out.println("as执行开始");
                Thread.sleep(1000);
                synchronized (s){
                    System.out.println("锁住as");
                }
            }
            System.out.println("as执行开始");

        }
    }
    public static void main(String[] args) {
        DeadLockTest deadLockTest = new DeadLockTest();
        Thread thread = new Thread(()-> {
            try {
                deadLockTest.new LockS().test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread1 = new Thread(()-> {
            try {
                deadLockTest.new LockA().test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread1.start();
    }
}