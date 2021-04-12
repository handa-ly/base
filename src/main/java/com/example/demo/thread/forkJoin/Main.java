package com.example.demo.thread.forkJoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: hanDa
 * @Date: 2021/4/1 17:54
 * @Version:1.0
 * @Description:
 */
public class Main {
    static Random random = new Random(0);
    static long random(){
        return random.nextInt(1000000);
    }
    public static void main(String[] args) {
        long[] array = new long[200000];
        long expectSum = 0;
        Long  startTime1 = System.currentTimeMillis();
        for (int i=0;i<array.length;i++){
            array[i] = random();
            expectSum +=array[i];
        }
        Long endTime1 = System.currentTimeMillis();
        System.out.println(String.format("split %d~%d ==>expectSum is %d and elapsed is %d.ms", 0, array.length,expectSum,endTime1 - startTime1));
        System.out.println("expectSum:" + expectSum);
        ForkJoinTask<Long> task = new Main().new SumTask(array,0,array.length);
        long startTime = System.currentTimeMillis();
        Long result = ForkJoinPool.commonPool().invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/Join sum:" + result + " in " +(endTime - startTime) + " ms." );
    }
    class SumTask extends RecursiveTask<Long>{
        static final int THRESHOLD = 500;
        long[] array;
        int start;
        int end;

        public SumTask(long[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end -start <= THRESHOLD){
                long sum = 0;
                for (int i=start;i<end;i++){
                    sum += this.array[i];
                }
                return sum;
            }
            int middle = (end  + start)/2;
            System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle,middle,end));
            SumTask sumTask = new SumTask(this.array,start,middle);
            SumTask sumTask1 = new SumTask(this.array,middle,end);
            invokeAll(sumTask,sumTask1);
            Long subResult = sumTask.join();
            Long subResult1 = sumTask1.join();
            Long result = subResult + subResult1;
            System.out.println("result = "+subResult + "+" + subResult1 +" ==>" + result);
            return result;
        }
    }
}