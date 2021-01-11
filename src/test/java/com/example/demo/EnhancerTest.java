package com.example.demo;

import com.example.demo.designMode.dyncproxy.aop.impl.GamePlayer;
import com.example.demo.designMode.factory.abstractFactory.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @Author: hanDa
 * @Date: 2020/12/3 15:41
 * @Version:1.0
 * @Description:
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
public class EnhancerTest {
    @Test
    public void testInvocationHandler() throws Exception {
        GamePlayer gamePlayer = new GamePlayer();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(GamePlayer.class);
        enhancer.setCallback((InvocationHandler) (proxy, method, args) -> method.invoke(gamePlayer,args));
        GamePlayer proxy = (GamePlayer) enhancer.create();
        assertEquals("Hello cglib!", proxy.test());
        assertNotEquals("Hello cglib!", proxy.toString());
    }

    @Test
    public void testMethodInterceptor() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(GamePlayer.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o,objects));
        GamePlayer playerProxy = (GamePlayer) enhancer.create();
        playerProxy.daguai();
    }

    @Test
    public void stock() {
        BigDecimal a = BigDecimal.ONE;
        for (int i=0;i<360;i++){
            a = a.multiply(new BigDecimal(0.9)).setScale(10,RoundingMode.HALF_UP);
        }
        System.out.println(a);
    }

    @Test
    public void collectionTest() {
        Collection ss = new ArrayList();
        ss.stream().forEach(System.out::println);
    }
}