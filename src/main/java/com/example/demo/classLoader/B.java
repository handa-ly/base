package com.example.demo.classLoader;
/**
    *@ClassName: B
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/4/2 16:56
    */
    
    
public class B extends A {
    private static int numB;
    private int numB2;

    static {
        System.out.println("B的静态字段 : " + numB);
        System.out.println("B的静态代码块");
    }

    {
        System.out.println("B的成员变量 : " + numB2);
        System.out.println("B的非静态代码块");
    }

    public B() {
        System.out.println("B的构造器");
    }

}
