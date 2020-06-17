package com.example.demo.aspect.demo2;

import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Method;

/**
    *@ClassName: MyPointcut
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/4/23 14:47
    */
    
    
public class MyPointcut extends NameMatchMethodPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        this.setMappedName("addUser");
//        this.setMappedName("*User");
        return super.matches(method, targetClass);
    }
}
