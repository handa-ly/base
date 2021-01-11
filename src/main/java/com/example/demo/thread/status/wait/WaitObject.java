package com.example.demo.thread.status.wait;

/**
 * @Author: hanDa
 * @Date: 2020/10/9 14:58
 * @Version:1.0
 * @Description: 当前对象被锁定，method-2线程 wait后释放锁，则method-1线程才能获取锁，调用notify唤醒锁竞争者线程，当前线程执行完后，其他线程方才能执行
 */
public class WaitObject {
    boolean available = false;
    synchronized void method_1(){
        //called by thread
        // 1.//access data area
        available=true;
        notify();
        System.out.println("notify other");
    }
     synchronized void method_2(){
        //called by thread
        // 2.
        while(!available){
            try{
                System.out.println("wait for notify()");
                wait();
                System.out.println("wait has been notified");
            }catch(InterruptedException e){

            }//accessdataarea}
        }
    }
}