package com.example.demo.designMode.flyWeight;

/**
 * @Author: hanDa
 * @Date: 2021/1/26 16:56
 * @Version:1.0
 * @Description:
 */
public class Flyweight {
    //内部状态intrinsicState作为成员变量，同一个享元对象其内部状态是一致的
    private String intrinsicState;
    public Flyweight(String intrinsicState) {
        this.intrinsicState=intrinsicState;
    }
    //外部状态extrinsicState在使用时由外部设置，不保存在享元对象中，即使是同一个对象
    public void operation(String extrinsicState) {
        //......
    }
}