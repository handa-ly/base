package com.example.demo.designMode.modleMethod;

/**
 * @Author: hanDa
 * @Date: 2020/9/28 18:30
 * @Version:1.0
 * @Description:
 */
public abstract class AbstractClass {
    protected  abstract void abstractMethod();
    public final void modelMethod(){
        this.abstractMethod();
    }

    public static void main(String[] args) {
        AbstractClass abstractClass = new CurrentClass();
        abstractClass.abstractMethod();
    }
}