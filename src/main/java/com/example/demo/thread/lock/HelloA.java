package com.example.demo.thread.lock;

/**
 * @Author: hanDa
 * @Date: 2021/4/9 11:43
 * @Version:1.0
 * @Description:
 */
public class HelloA {
    public static class Main {
        public static void main(String[] args) throws Exception {
           Thread t = new Thread(()->{

              /* while (!Thread.interrupted()){

               }*/
//               System.out.println("Thread.interrupted:" + Thread.interrupted());
               Thread join = new Thread(()->{
                   System.out.println("join");
                   try {
                       Thread.sleep(1000);
                       System.out.println("睡眠结束！");
                   } catch (InterruptedException e) {
                       System.out.println("Thread.sleep(1000)");
                   }
               });
               join.start();
               try {
                   join.join();
                   System.out.println("11111111111111111");
               } catch (InterruptedException e) {
                   System.out.println("=========join.start()======");
//                   e.printStackTrace();
               }
//               System.out.println(Thread.interrupted());
           });
            t.start();
//            System.out.println(t.isInterrupted());
            t.interrupt();
//            System.out.println(t.isInterrupted());
        }
    }
}