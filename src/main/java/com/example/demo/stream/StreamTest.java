package com.example.demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: hanDa
 * @Date: 2021/6/3 15:10
 * @Version:1.0
 * @Description:
 */
public class StreamTest {
    public static void main(String[] args) {


        //一 流的概念
        //将集合元素看做一种流 使用strem的api对流进行操作 （筛选 排序 聚合）
        //由数组和集合创建 对流的操作有两种 1.中间操作返回流 2.终端操作 流只能进行一次终端操作 产生新的值或者集合
        //特性：stream1.不储存数据  计算得到值 2.延迟执行特性 执行终端操作时才会执行中间操作

        //二 创建流的方式
        //collection.stream 创建流
        List<String> list = Arrays.asList("a","b","c");
        Stream<String> stream = list.stream();
        Stream<String> parallelStream = list.parallelStream();

        //Arrays.stream(T[] array)创建流 int long double
        int[] array = {1,2,3,45,5};
        IntStream intStream = Arrays.stream(array);

        //通过stream的静态方法创建流 of， iterate ，generate
        Stream<Integer> integerStream = Stream.of(1,2,3,4);
        Stream<String> stringStream = Stream.of("1");
        Stream<Integer> integerStream1 = Stream.iterate(0,(x)->x+3).limit(4);
        Stream<Double>  doubleStream = Stream.generate(Math::random).limit(3);
        //stream顺序流 主线程顺序执行流 parallelStream是并行流   并行流可以转为顺序流

        //三 stream的使用

        //optional的使用
        Objects.requireNonNull(null);
     }
}