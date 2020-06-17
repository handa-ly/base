package com.example.demo.aspect.advice;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * @ClassName: ThrowsLogAdvice
 * @Description: TODO
 * @Author: handa
 * @Date: 2020/4/23 11:22
 */

@Slf4j
public class ThrowsLogAdvice implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] args, Object target,
                              Throwable exeptionClass) {
        // 获取被调用的类名
        String targetClassName = target.getClass().getName();
        // 获取被调用的方法名
        String targetMethodName = method.getName();
        // 日志格式字符串
        String logInfoText = "异常通知：执行" + targetClassName + "类的"
                + targetMethodName + "方法时发生异常";
        // 将日志信息写入配置的文件中
        log.info(logInfoText);
    }

    public void afterThrowing(RuntimeException e) {
        System.out.println(e.getMessage());
    }
}
