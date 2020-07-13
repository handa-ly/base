package com.example.demo.aop.agency.dyn.test;

import com.example.demo.aop.IHello;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: HelloInvocationHandler
 * @Description: TODO
 * @Author: handa
 * @Date: 2020/7/10 11:17
 */

public class HelloInvocationHandler implements InvocationHandler {

    private Object delegate;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        method.invoke(this.delegate, args);
        return null;
    }

    public<T> T instantProxy(T delegate) {
        this.delegate = delegate;
        return (T) Proxy.newProxyInstance(this.delegate.getClass().getClassLoader(),
            this.delegate.getClass().getInterfaces(), this);
    }
}
