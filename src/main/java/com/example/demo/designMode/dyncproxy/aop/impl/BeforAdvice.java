package com.example.demo.designMode.dyncproxy.aop.impl;

import com.example.demo.designMode.dyncproxy.aop.IAdvice;

/**
 * @Author: hanDa
 * @Date: 2020/12/2 17:07
 * @Version:1.0
 * @Description:
 */
public class BeforAdvice implements IAdvice {
  @Override
  public void exec(){
      System.out.println("before......");
  }
}