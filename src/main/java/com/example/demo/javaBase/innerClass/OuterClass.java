package com.example.demo.javaBase.innerClass;

import com.example.demo.qualifier.test.Interface;

/**
 * @Author: hanDa
 * @Date: 2020/12/16 20:41
 * @Version:1.0
 * @Description:
 */
public class OuterClass {
    private int d = 1;
    private int a = 2;

    // 静态内部类  FIXME
    public static class Inner {
        public void test() {
            // 静态内部类可以访问外部类的静态成员
            // 并且它只能访问静态的
            System.out.println("ss");
        }

    }

    // 定义一个成员内部类 FIXME
    public class Inner2 {
        private int a = 8;

        public void doSomething() {
            // 直接访问外部类对象
            System.out.println(d);
            System.out.println(a);// 直接访问a，则访问的是内部类里的a

            // 如何访问到外部类里的a呢？
            System.out.println(OuterClass.this.a);
        }

    }

    public void doSomething() {
        int b = 2;
        final int c = 3;
        // 定义一个局部内部类 FIXME
        class Inner3 {
            public void test() {
                System.out.println("Hello World");
                System.out.println(a);

                // 不可以访问非final的局部变量
                // error: Cannot refer to a non-final variable b inside an inner
                // class defined in a different method
                // System.out.println(b);

                // 可以访问final变量
                System.out.println(c);
            }
        }

        // 创建局部内部类的实例并调用方法
        new Inner3().test();
        //匿名内部类，实现某个接口或者父类，一般作为方法参数，或者返回值 FIXME
        //没有名字的局部内部类，隐式实现接口或者继承类
        new AnonymouseInnerInterface() {
            @Override
            public void test() {
              test:{
                if (true){
                    break test;
                }
              }
            }
        };
    }
    public interface AnonymouseInnerInterface{
        public void test();
    }
}