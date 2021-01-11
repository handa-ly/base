package com.example.demo.designMode.dyncproxy.aop.impl;

import com.example.demo.designMode.dyncproxy.aop.IGamePlayer;
import org.checkerframework.checker.index.qual.PolyUpperBound;

/**
 * @Author: hanDa
 * @Date: 2020/12/2 15:57
 * @Version:1.0
 * @Description:
 */
public class GamePlayer implements IGamePlayer {
    @Override
    public void daguai() {
        System.out.println("打怪！");
    }

    @Override
    public void shengji() {
        System.out.println("升级！");
    }
    public String test(){
        return "s";
    }

}