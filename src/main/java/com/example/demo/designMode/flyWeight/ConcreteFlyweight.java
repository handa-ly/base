package com.example.demo.designMode.flyWeight;

/**
 * @Author: hanDa
 * @Date: 2021/1/26 16:58
 * @Version:1.0
 * @Description:
 */
public class ConcreteFlyweight extends Flyweight{
    public ConcreteFlyweight(String intrinsicState) {
        super(intrinsicState);
    }

    @Override
    public void operation(String extrinsicState) {
        super.operation(extrinsicState);
    }
}