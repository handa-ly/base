package com.example.demo.thread.joinwaitnotify.interrupt;/**
 * @Author handa
 * Description:
 * Date: Created in 13:49 2020/1/15
 * Modified By:
 */

/**
 * @description:
 * @author: handa
 * @time: 2020/1/15 13:49
 */
public class InterruptTest extends Thread {
    @Override
    public void run() {
        interrupt();
        System.out.println(this.isInterrupted());
        System.out.println(this.interrupted());
        System.out.println(this.interrupted());
        System.out.println(this.isInterrupted());

        System.out.println("interrupt!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread = new InterruptTest();
        thread.start();
    }
}
