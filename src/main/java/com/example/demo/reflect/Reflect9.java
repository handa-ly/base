package com.example.demo.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: da.Han
 * @createDate: 2020/7/14
 * @version: 1.0
 */
public class Reflect9 {
  public static void main(String[] args) throws Exception{
    MyInvocationHandler<Subject> invocationHandler =  new Reflect9().new MyInvocationHandler();
    Subject sub =  invocationHandler.bind(new Reflect9().new RealSubject());
    String info = sub.say("mazaiting", 23);
    System.out.println(info);
    /**
     * mazaiting, 23
     */
  }

  public class MyInvocationHandler<T> implements InvocationHandler {
    private Object obj = null;
    public T bind(Object obj) {
      this.obj = obj;
      return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(),
          obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      Object temp = method.invoke(obj, args);
      return temp;
    }

  }

  public class RealSubject implements Subject{

    @Override
    public String say(String name, int age) {
      return name + ", " + age;
    }

  }

  public interface Subject{
    String say(String name, int age);
  }
}
