package com.example.demo.aop.agency.dyn.test;

import com.example.demo.aop.Hello;
import com.example.demo.aop.IHello;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DynProxyTes
 * @Description: TODO
 * @Author: handa
 * @Date: 2020/7/10 11:14
 */

public class DynProxyTes {

  public static void main(String[] args) {
    //
    List list = new ArrayList();
    list.add(3);
//    int hello1 = list.get(0);
    IHello hello = new HelloInvocationHandler().instantProxy(new Hello());
    hello.sayHello("hello");
  }
  public<T> String test(T s){
    String ss = (String) s;
    return ss;
  }
}
