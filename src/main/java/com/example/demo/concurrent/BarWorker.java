package com.example.demo.concurrent;/**
 * @Author handa
 * Description:
 * Date: Created in 14:53 2020/1/16
 * Modified By:
 */

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @description:
 * @author: handa
 * @time: 2020/1/16 14:53
 */
public class BarWorker implements Runnable {
    private String name;
    private static AtomicBoolean exists = new AtomicBoolean(true);

    public BarWorker(String name) {
        this.name = name;
    }

//    @lombok.SneakyThrows
    @Override
    public void run() {
        if (exists.compareAndSet(false, true)) {
            System.out.println(name + "enter");
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "leave");
        } else {
            System.out.println(name + "give up");
        }
    }

    public static void main(String[] args) {
        BarWorker barWorker1 = new BarWorker("====BW1====");
        BarWorker barWorker2 = new BarWorker("===BW2====");
        new Thread(barWorker1).start();
        new Thread(barWorker2).start();
    }
}
