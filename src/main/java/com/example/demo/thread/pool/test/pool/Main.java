package com.example.demo.thread.pool.test.pool;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: hanDa
 * @Date: 2021/3/31 11:56
 * @Version:1.0
 * @Description:
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
      log.info("start main---");
      new Thread(()->{
          log.info("run task...........");
          System.out.println(Thread.currentThread().getName());
          Main main = new Main();
          main.test();
          main.test1();
          main.test2();
          main.test3();
      }).start();
      log.info("end main..........");
    }
    private void test(){
        LocalThread.threadLocal.set(0);
        LocalThread.threadLocal1.set(10);
    }
    private void test1()
    {
        LocalThread.threadLocal.set(1);
        LocalThread.threadLocal1.set(11);

    }
    private void test2(){
        LocalThread.threadLocal.set(3);
        LocalThread.threadLocal1.set(33);
    }
    private void test3(){
        //自动关闭功能AutoCloseable
        try (LocalThread ignored = new LocalThread()){
            System.out.println(LocalThread.threadLocal.get());
            System.out.println(LocalThread.threadLocal1.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(LocalThread.threadLocal.get());
        System.out.println(LocalThread.threadLocal1.get());
    }

    public static class LocalThread implements AutoCloseable{
        public static final ThreadLocal<Integer>  threadLocal = new ThreadLocal();
        public static final ThreadLocal<Integer>  threadLocal1 = new ThreadLocal();
        @Override
        public void close() throws Exception {
               threadLocal.remove();
               threadLocal1.remove();
        }
    }
}