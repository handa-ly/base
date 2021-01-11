package com.example.demo.designMode.dyncproxy.test;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: hanDa
 * @Date: 2020/12/15 14:02
 * @Version:1.0
 * @Description:
 */
public class TestCglib {
    public TestCglib() {
    }

    public class Pig{
        public void update() {
            System.out.println("PeopleDao.update()");
        }
        public void select() {
            System.out.println("this is a pig");
        }
    }
    public class InterceptHandler implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("exce method："+ method.getAnnotatedReturnType());

            return methodProxy.invokeSuper(o, objects);
        }
    }

    public static void main(String[] args) {
        TestCglib testCglib = new TestCglib();
        InterceptHandler interceptHandler = testCglib.new InterceptHandler();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Pig.class);
        enhancer.setCallback(interceptHandler);
        Pig pig = (Pig) enhancer.create(new Class[]{TestCglib.class},new Object[]{testCglib});
        pig.select();
    }
}