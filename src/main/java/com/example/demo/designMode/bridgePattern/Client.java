package com.example.demo.designMode.bridgePattern;

/**
 * @Author: hanDa
 * @Date: 2021/1/27 10:15
 * @Version:1.0
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
//定义一个实现化角色
        Implementor imp = new ConcreteImplementor1();
//定义一个抽象化角色
        Abstraction abs = new RefinedAbstraction(imp);
//执行行文
        abs.request();
    }
}