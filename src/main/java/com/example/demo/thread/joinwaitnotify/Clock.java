package com.example.demo.thread.joinwaitnotify;/**
 * @Author handa
 * Description:
 * Date: Created in 11:17 2020/1/15
 * Modified By:
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: handa
 * @time: 2020/1/15 11:17
 */
public class Clock {
    private String currentTime = String.valueOf(System.currentTimeMillis());

    /**
     * 获取当前时间
     */
    class TimeThread extends Thread {
        @Override
        public void run() {
            String pattern = "yyyy-MM-dd hh:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            currentTime = sdf.format(new Date());
//            while (true){
            synchronized (currentTime.intern()) {
//                currentTime.notify();
            }
            if (currentTime == null) {
                return;
            }
//            }
        }
    }

    /**
     * 显示时间
     */
    class ShowThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (currentTime.intern()) {
                    try {
                        currentTime.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("现在的时间是:" + currentTime);
                }
                if (currentTime == null) {
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Clock clock = new Clock();
        ShowThread showThread = clock.new ShowThread();
        TimeThread timeThread = clock.new TimeThread();
        timeThread.start();
        showThread.start();
        Thread.currentThread().sleep(1000);
        showThread.interrupt();
    }
}
