package com.example.demo.designMode.dyncproxy.aop.test1;

import com.example.demo.designMode.dyncproxy.aop.IGamePlayer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.example.demo.designMode.dyncproxy.aop.impl.GamePlayIH;
import com.example.demo.designMode.dyncproxy.aop.impl.GamePlayer;
/**
 * @Author: hanDa
 * @Date: 2020/12/2 15:58
 * @Version:1.0
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        //目标对象
        IGamePlayer iGamePlayer = new GamePlayer();
        //调用处理程序，method.invoke
        InvocationHandler invocationHandler = new GamePlayIH(iGamePlayer);
        ClassLoader classLoader = iGamePlayer.getClass().getClassLoader();
        //代理对象
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(classLoader, iGamePlayer.getClass().getInterfaces(), invocationHandler);
        proxy.daguai();
        proxy.shengji();
    }
}