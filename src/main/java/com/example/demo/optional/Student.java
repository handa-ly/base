package com.example.demo.optional;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: hanDa
 * @Date: 2020/11/16 20:26
 * @Version:1.0
 * @Description:
 */
@Slf4j
@Data
public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}