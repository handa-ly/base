package com.example.demo.aop.agency.dyn.ciglib;

import org.springframework.cglib.proxy.Enhancer;

public class CiglibTest {

  public static void main(String[] args) {
      Enhancer enhancer = new Enhancer();
      enhancer.setSuperclass(CiglibAop.class);
      enhancer.setCallback(new LogIntercept());
      CiglibAop ciglibAop = (CiglibAop) enhancer.create();
      ciglibAop.test();

  }
}
