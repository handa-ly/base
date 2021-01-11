package com.example.demo.designMode.builder;

import lombok.Data;

/**
 * @Author: hanDa
 * @Date: 2020/9/15 18:03
 * @Version:1.0
 * @Description:
 */
@Data
public class Monster {
    private int size; //尺寸
    private int x;  //x坐标
    private int y; //y坐标
    private int aggressivity; //怪兽攻击力
    private int defenseValue;  //怪兽防御值

    public Monster(int size, int x, int y, int aggressivity, int defenseValue) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.aggressivity = aggressivity;
        this.defenseValue = defenseValue;
    }
}