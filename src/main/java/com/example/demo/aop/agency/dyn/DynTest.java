package com.example.demo.aop.agency.dyn;

import com.example.demo.aop.Hello;
import com.example.demo.aop.IHello;

/**
 * @ClassName: DynTest @Description: TODO @Author: handa @Date: 2020/7/9 18:05
 */
public class DynTest {

  public static void main(String[] args) {
    IHello hello = (IHello) new DynaProxyHello().bind(new Hello());
    hello.sayGoogBye("Double J");
    hello.sayHello("Double J");
  }
}
