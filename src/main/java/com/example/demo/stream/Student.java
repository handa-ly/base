package com.example.demo.stream;

import lombok.Data;

/**
    *@ClassName: Student
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/4/24 16:44
    */
    

@Data
public class Student {
  private Integer id;
  private String name;
  private Integer age;

  public Student(Integer id, String name, Integer age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }
}
