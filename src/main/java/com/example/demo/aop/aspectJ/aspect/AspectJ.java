package com.example.demo.aop.aspectJ.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: da.Han
 * @createDate: 2020/7/15
 * @version: 1.0
 */
@Component
@Aspect
public class AspectJ {

  @Pointcut("execution(* com.example.demo.aop.aspectJ..*.*(..))")
//  @Pointcut("execution(* com.example.demo.aop.aspectJ.TargetObject.*(..))")
  public void pointJoin(){}

  @Before("pointJoin()")
  public void before(JoinPoint joinPoint){
    System.out.println("aspect切面动态开始======================");
    joinPoint.getSignature();
    joinPoint.getArgs();
    joinPoint.toString();
    joinPoint.getKind();
    joinPoint.getSourceLocation();
    joinPoint.getStaticPart();
    joinPoint.getTarget();
    joinPoint.getThis();
    System.out.println(joinPoint.toString());
  }
}
