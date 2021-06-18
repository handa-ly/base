package com.example.demo.stream;

import com.example.demo.stream.test.Person;
import org.apache.poi.ss.formula.functions.T;

import java.io.Console;
import java.util.*;
import java.util.stream.Collectors;
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
        sort();
    }

    public static void sort(){
        List<Person> personList = new ArrayList<Person>();

        personList.add(new Person("Sherry", 9000, 24, "female", "New York"));
        personList.add(new Person("Tom", 8900, 22, "male", "Washington"));
        personList.add(new Person("Jack", 9000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 8800, 26, "male", "New York"));
        personList.add(new Person("Alisa", 9000, 26, "female", "New York"));
        List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName).collect(Collectors.toList());
        List<String> newList1 = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName).collect(Collectors.toList());
        System.out.println(newList);
        System.out.println(newList1);
    }

    private final int value;

    public StreamTest(int value) {
        this.value = value;
    }

    public int compareTo() {
        return 0;
    }
}