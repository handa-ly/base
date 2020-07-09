package com.example.demo.aop;

/**
 * @ClassName: IHello @Description: TODO @Author: handa @Date: 2020/7/9 17:29
 */
public interface IHello {

  /**
   * 5 * 假设这是一个业务方法 6 * @param name 7
   */
  void sayHello(String name);

  /**
   * 10 * 业务处理B方法 11 * @param name 12
   */
  void sayGoogBye(String name);
}
