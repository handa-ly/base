package com.example.demo.classLoader;
/**
    *@ClassName: A
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/4/2 16:56
    */
    
    
public class A {
    private static int numA;
    private int numA2;

    static {
        System.out.println("A的静态字段 : " + numA);
        System.out.println("A的静态代码块");
    }

    {
        System.out.println("A的成员变量  : " + numA2);
        System.out.println("A的非静态代码块");
    }

    public A() {
        System.out.println("A的构造器");
    }

}
