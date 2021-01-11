package com.example.demo.designMode.dyncproxy.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: hanDa
 * @Date: 2020/12/14 15:40
 * @Version:1.0
 * @Description:
 */
public class TestAop {

     interface IStudent{
         void test();
     }


    public class Student implements IStudent{
        @Override
        public void test() {
            System.out.println("this is a student!");
        }
    }

    public class StudentInvocationHandler implements InvocationHandler{
         private Object object;
        public StudentInvocationHandler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(proxy,args);
        }
    }

    public static void main(String[] args) {
        Student student = new TestAop().new Student();
        StudentInvocationHandler studentInvocationHandler = new TestAop().new StudentInvocationHandler(student);
        IStudent student1 = (IStudent) Proxy.newProxyInstance(student.getClass().getClassLoader(), Student.class.getInterfaces(),studentInvocationHandler);
        student1.test();
     }
}