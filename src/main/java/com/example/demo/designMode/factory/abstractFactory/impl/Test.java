package com.example.demo.designMode.factory.abstractFactory.impl;

import com.example.demo.designMode.factory.abstractFactory.impl.factory.GameAFactory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @Author: hanDa
 * @Date: 2020/11/18 11:55
 * @Version:1.0
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        /*GameAFactory gameAFactory = new GameAFactory();
        gameAFactory.createGame1();*/
//        System.out.println(Optional.empty().orElse(2));
        Set set =new HashSet();
        set.add(null);
        set.add(null);
        System.out.println(set.toString());
    }
}