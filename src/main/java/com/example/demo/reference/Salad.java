package com.example.demo.reference;

import com.mysql.cj.protocol.PacketSentTimeHolder;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Author: hanDa
 * @Date: 2021/4/1 11:22
 * @Version:1.0
 * @Description:
 */
public class Salad extends WeakReference<Apple> {
    public Salad(Apple referent) {
        super(referent);
    }

    public static void main(String[] args) {
        DecimalFormat df4 = new DecimalFormat("#,##0.#########");
        System.out.println(df4.format(new BigDecimal(3613.61)));
        System.out.println(df4.format(new BigDecimal(3613.613)));
        System.out.println(df4.format(new BigDecimal(3613.6135)));
        System.out.println(df4.format(new BigDecimal(3613.6136)));

        Salad salad = new Salad(new Apple("红富士"));
        //通过WeakReference的get()方法获取Apple
        System.out.println("Apple:" + salad.get());
        System.gc();
        try {
            //休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //如果为空，代表被回收了
        if (salad.get() == null) {
            System.out.println("clear Apple。");
        }
    }
}