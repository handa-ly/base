package com.example.demo.designMode.dyncproxy.aop.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: hanDa
 * @Date: 2020/12/2 15:40
 * @Version:1.0
 * @Description:
 */
public class GamePlayIH implements InvocationHandler {
    private Class cls = null;
    private Object obj = null;

    public GamePlayIH(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.obj,args);
    }
}
