package com.example.demo.aspect.demo3;

import org.springframework.aop.support.ControlFlowPointcut;

import java.lang.reflect.Method;

/**
    *@ClassName: MyControlFlowPointcut
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/4/23 15:58
    */
    
    
public class MyControlFlowPointcut extends ControlFlowPointcut {
    public MyControlFlowPointcut(Class<?> clazz, String methodName) {
        super(clazz, methodName);
    }
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return method.getName().equals("addUser");
    }
}
