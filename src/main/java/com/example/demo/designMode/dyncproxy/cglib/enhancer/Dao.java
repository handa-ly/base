package com.example.demo.designMode.dyncproxy.cglib.enhancer;

/**
 * @Author: hanDa
 * @Date: 2020/12/2 21:51
 * @Version:1.0
 * @Description:
 */
public class Dao {

    public void update() {
        System.out.println("PeopleDao.update()");
    }

    public void select() {
        System.out.println("PeopleDao.select()");
    }
}