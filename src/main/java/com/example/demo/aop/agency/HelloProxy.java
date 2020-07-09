package com.example.demo.aop.agency;

import com.example.demo.aop.IHello;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: HelloProxy @Description: TODO @Author: handa @Date: 2020/7/9 17:32
 */
@Slf4j
public class HelloProxy implements IHello {

  private final IHello hello;

  public HelloProxy(IHello hello) {
    this.hello = hello;
  }

  @Override
  public void sayHello(String name) {
    log.debug("sayHello method start.");
    hello.sayHello(name);
    log.debug("sayHello method end!");

  }
}
