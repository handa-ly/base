package com.example.demo.qualifier.test1;

import com.example.demo.qualifier.test.Interface;

/**
 * @Author: hanDa
 * @Date: 2020/11/9 10:57
 * @Version:1.0
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Interface inter = new Interface() {
            @Override
            public void test() {
                System.out.println("ss");
            }
        };
       inter.test1();
    }

}
