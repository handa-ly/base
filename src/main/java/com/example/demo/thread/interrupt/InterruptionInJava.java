package com.example.demo.thread.interrupt;

/**
 * @Author: hanDa
 * @Date: 2020/10/14 15:07
 * @Version:1.0
 * @Description:
 */
public class InterruptionInJava implements Runnable{
    private volatile static boolean on = false;
    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new InterruptionInJava(),"InterruptionInJava");
        //start thread
        testThread.start();
        Thread.sleep(1000);
        InterruptionInJava.on = true;
        //interrupt thread
        testThread.interrupt();
//        testThread.isInterrupted()
//        System.out.println(testThread.isInterrupted());
        System.out.println("main end");
//        Thread.interrupted()
    }
    @Override
    public void run() {
        while(true){
           /* if(Thread.currentThread().isInterrupted()){
                System.out.println("---中断状态！");
                return;
            }else {
                System.out.println("---非中断状态！");
            }*/
            /*if (Thread.interrupted()){
            System.out.println("中断状态！");
        }else {
            System.out.println("非中断状态！");
        }*/
            if(Thread.currentThread().isInterrupted()){
                System.out.println("---中断状态！");
            }else {
                System.out.println("---非中断状态！");
            }

//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                System.out.println("caught exception: "+e);
//            }
        }
    }
}