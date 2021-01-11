package com.example.demo.designMode.builder;

/**
 * @Author: hanDa
 * @Date: 2020/9/15 18:09
 * @Version:1.0
 * @Description:
 */
public class Clienter {
    public static void main(String[] args) {
        //首先拥有一个建造者。 在游戏界面可能就是一个按钮。
        Director director = new Director();
        //我要高品质，并且加音乐的
        Map createMap = director.createMap("100%", true);
        System.out.println(createMap);
        //中品质的画面
        Map createMap1 = director.createMap("80%", true);
        System.out.println(createMap1);
        //电脑太差了，低品质的
        Map createMap2 = director.createMap("50%", false);
        System.out.println(createMap2);
    }
}