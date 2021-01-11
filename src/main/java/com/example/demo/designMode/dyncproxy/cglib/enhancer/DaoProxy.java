package com.example.demo.designMode.dyncproxy.cglib.enhancer;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: hanDa
 * @Date: 2020/12/2 21:51
 * @Version:1.0
 * @Description:
 */
public class DaoProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object object, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
        System.out.println("Before Method Invoke");
        proxy.invokeSuper(object, objects);
        System.out.println("After Method Invoke");

        return object;
    }

}