package com.example.demo.aop.agency.dyn.ciglib;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/** @ClassName: LogIntercept @Description: TODO
 * @Author: handa @Date: 2020/7/10 18:20 */
public class LogIntercept implements MethodInterceptor {

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy){
    try {
      System.out.println("开始");
      methodProxy.invokeSuper(o,objects);
      System.out.println("结束");
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return null;
  }
}
