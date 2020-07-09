package com.example.demo.aop;

/**
 * @ClassName: Hello @Description: TODO @Author: handa @Date: 2020/7/9 17:31
 */
public class Hello implements IHello {

  @Override
  public void sayHello(String name) {
    System.out.println("Hello " + name);
  }

  @Override
  public void sayGoogBye(String name) {
    System.out.println(name + " GoodBye!");
  }
}
