package com.example.demo.concurrent.blocking;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Author: hanDa
 * @Date: 2020/10/26 20:39
 * @Version:1.0
 * @Description:
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<String> b = new ArrayBlockingQueue(1);
        /*b.add("ss");
        System.out.println(b.size());
        System.out.println(b.contains("ss"));
        ArrayList<String> a = new ArrayList<>();
        b.drainTo(a);
        System.out.println("a:"+a.size()+",b:"+b.size());*/

        //add,offer 容器满时，add报错，offer返回false
        System.out.println("------------");
        System.out.println(b.add("a"));
        System.out.println(b.offer("a"));
        //poll,remove,容器空时poll返回null，remove报错
        System.out.println("------------");
        System.out.println(b.remove());
        System.out.println(b.poll());
        //peek,element,容器为空时，peek返回null，element报错
        System.out.println("------------");
        System.out.println(b.peek());
        System.out.println(b.element());

    }
}