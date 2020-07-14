package com.example.demo.reflect;

import lombok.Data;

/**
 * @description:
 * @author: da.Han
 * @createDate: 2020/7/14
 * @version: 1.0
 */
@Data
public abstract class Parent{
  public String parentName;
  public void parentBehaviour(){
    System.out.println("------");
  }
  public abstract String commonBehaviour();
}
