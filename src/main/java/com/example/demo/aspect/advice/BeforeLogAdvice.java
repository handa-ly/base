package com.example.demo.aspect.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
    *@ClassName: BeforeLogAdvice
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/4/23 11:05
    */
    
    
public class BeforeLogAdvice implements MethodBeforeAdvice {
    private Logger logger = LoggerFactory.getLogger(BeforeLogAdvice.class);
    @Override
    public void before(Method method, Object[] objects, Object target) throws Throwable {
        String targetClassName = target.getClass().getName();
        String targetMethodName = method.getName();

        // args[0] = "Juve";//可以改变参数

        String logInfoText = "前置通知：" + targetClassName + "类的"
                + targetMethodName + "方法开始执行";
        logger.info(logInfoText);
    }
}
