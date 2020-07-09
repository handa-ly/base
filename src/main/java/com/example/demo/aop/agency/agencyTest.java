package com.example.demo.aop.agency;

import com.example.demo.aop.Hello;
import com.example.demo.aop.IHello;

/**
 * @ClassName: agencyTest @Description: TODO @Author: handa @Date: 2020/7/9 17:37
 */
public class agencyTest {

  public static void main(String[] args) {
    //
    IHello hello = new Hello();
    IHello helloProxy = new HelloProxy(hello);
    helloProxy.sayHello("hello");
  }
}
