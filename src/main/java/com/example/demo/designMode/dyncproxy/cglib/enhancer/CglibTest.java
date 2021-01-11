package com.example.demo.designMode.dyncproxy.cglib.enhancer;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @Author: hanDa
 * @Date: 2020/12/2 21:52
 * @Version:1.0
 * @Description:
 */
public class CglibTest {

    public static void main(String[] args) {
        DaoProxy daoProxy = new DaoProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dao.class);
        enhancer.setCallback(daoProxy);

        Dao dao = (Dao)enhancer.create();
        dao.update();
        dao.select();
    }
}