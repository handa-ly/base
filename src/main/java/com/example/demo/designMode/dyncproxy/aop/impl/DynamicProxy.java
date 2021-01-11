package com.example.demo.designMode.dyncproxy.aop.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Author: hanDa
 * @Date: 2020/12/2 17:04
 * @Version:1.0
 * @Description:
 */
public class DynamicProxy<T> {
    public static <T> T newProxyInstance(ClassLoader classLoader, Class<?>[] cls, InvocationHandler invocationHandler){
        if (true){
            new BeforAdvice().exec();
        }
        return (T) Proxy.newProxyInstance(classLoader,cls,invocationHandler);
    }

}