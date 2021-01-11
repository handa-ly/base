package com.example.demo.aspect.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
    *@ClassName: LogAroundAdvice
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/4/23 11:19
    */
    
    
public class LogAroundAdvice implements MethodInterceptor {
    private Logger logger = LoggerFactory.getLogger(LogAroundAdvice.class);
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 获取被调用的方法名
        String targetMethodName = invocation.getMethod().getName();

        /*
        invocation.getArguments()[0] = "Alex";//也可以得到或者改变要执行的方法的参数
         */

        long beginTime = System.currentTimeMillis();
        invocation.proceed(); //调用被拦截的方法
        long endTime = System.currentTimeMillis();

        // 日志格式字符串
        String logInfoText = "环绕通知：" + targetMethodName + "方法调用前时间" + beginTime
                + "毫秒," + "调用后时间" + endTime + "毫秒";
        // 将日志信息写入配置的文件中
        logger.info(logInfoText);

        //这儿相当于强行将拦截的方法的返回值设成了null
        return null;
        //return invocation.proceed();
    }
}
