package com.example.demo.stream.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author: hanDa
 * @Date: 2021/6/4 16:58
 * @Version:1.0
 * @Description:
 */
@Data
public class Person {
    private String name;  // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String sex; //性别
    private String area;  // 地区

    public Person() {
    }

    // 构造方法
    public Person(String name, int salary, int age,String sex,String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }

    public Person(String name, int salary, String sex, String area) {
        this.name = name;
        this.salary = salary;
        this.sex = sex;
        this.area = area;
    }

    public static void main(String[] args) {
      List<Integer> list = Arrays.asList(1,2,4,5,6,7,7,8,9,9,0);
        Stream<Integer> stream = list.stream();
        stream.filter(t ->t > 7).forEach(System.out::println);
    }
}