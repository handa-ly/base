package com.example.demo.aop.agency.dyn;

import com.example.demo.aop.Hello;
import com.example.demo.aop.IHello;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DynTest @Description: TODO @Author: handa @Date: 2020/7/9 18:05
 */
public class DynTest {

  public static void main(String[] args) {
    List list = new ArrayList();
    list.add(3);
    System.out.println(list.get(0));
    IHello hello = (IHello) new DynaProxyHello().bind(new Hello());
    hello.sayGoogBye("Double J");
    hello.sayHello("Double J");
  }
}
