package com.example.demo.designMode.dyncproxy.aop.test1;

import com.example.demo.designMode.dyncproxy.aop.IGamePlayer;
import com.example.demo.designMode.dyncproxy.aop.impl.DynamicProxy;
import com.example.demo.designMode.dyncproxy.aop.impl.GamePlayIH;
import com.example.demo.designMode.dyncproxy.aop.impl.GamePlayer;

import java.lang.reflect.InvocationHandler;

/**
 * @Author: hanDa
 * @Date: 2020/12/2 17:12
 * @Version:1.0
 * @Description:
 */
public class Client1 {
    public static void main(String[] args) {
        IGamePlayer iGamePlayer = new GamePlayer();
        InvocationHandler invocationHandler = new GamePlayIH(iGamePlayer);
        ClassLoader classLoader = iGamePlayer.getClass().getClassLoader();
        IGamePlayer iGamePlayer1 = DynamicProxy.newProxyInstance(classLoader,iGamePlayer.getClass().getInterfaces(),invocationHandler);
        iGamePlayer1.shengji();
    }
}