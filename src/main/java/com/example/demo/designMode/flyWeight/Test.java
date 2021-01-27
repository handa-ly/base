package com.example.demo.designMode.flyWeight;

/**
 * @Author: hanDa
 * @Date: 2021/1/26 17:46
 * @Version:1.0
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Integer i1 = 12;
        Integer i2 = 12;
        System.out.println(i1 == i2);

        Integer b1 = 128;
        Integer b2 = 128;
        System.out.println(b1 == b2);
    }

}