package com.example.demo.concurrent.complateFuture;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * @Author: hanDa
 * @Date: 2021/3/26 10:57
 * @Version:1.0
 * @Description:
 */
public class ComplateFutureTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //runAsyncTest();
        compareGetAndJoin();
//        CompletableFuture<Supplier<Integer>> completableFuture = new CompletableFuture();
        /*completableFuture.complete(() -> {
            try {
                Thread.sleep(100);
                return 1000;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });*/
    }

    private static void compareGetAndJoin() throws InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            int i = 1/0;
            return 100;
        });
        CompletableFuture<Double> completableFuture1 = CompletableFuture.supplyAsync(() -> new Double(20));
        CompletableFuture completableFuture2 = CompletableFuture.allOf(completableFuture1,completableFuture);
        try {
            completableFuture.get();
            completableFuture.join();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }

    private static void runAsyncTest() {
        CompletableFuture completableFuture = CompletableFuture.runAsync(()->{
            String ss = null;
            try {
                Thread.sleep(1000);
                System.out.println("s");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(ss.toLowerCase(Locale.ROOT));
        });
        completableFuture.thenAccept((result)->{
            System.out.println(result);
        });
        completableFuture.exceptionally((e) -> {
            ((Exception)e).printStackTrace();
            System.out.println(((Exception) e).getMessage());
              return null;
          });
        System.out.println("ss-==ss");
        while (true){

        }
    }
}