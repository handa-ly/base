package com.example.demo.designMode.builder;

/**
 * @Author: hanDa
 * @Date: 2020/9/15 18:04
 * @Version:1.0
 * @Description:
 */
public class Resources {
    //模拟下载了防御塔
    public DefenseTower getDefenseTower(){
        return new DefenseTower(12, 50, 50, 100, 80);
    }
    //模拟下载了道路
    public Road getRoad(){
        return new Road(300, 2000, 50, 50);
    }
    //模拟下载了树
    public Tree getTree(){
        return new Tree("绿色", 5, 20, 100);
    }
    //模拟下载了野怪
    public Monster getMonster(){
        return new Monster(18, 60, 150, 75, 40);
    }

}