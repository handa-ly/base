package com.example.demo.designMode.builder;

import lombok.Data;

/**
 * @Author: hanDa
 * @Date: 2020/9/15 18:01
 * @Version:1.0
 * @Description:
 */
@Data
public class DefenseTower {
    //防御塔尺寸
    private int x;
    //防御塔x坐标
    private int y;
    //防御塔y坐标
    private int aggressivity;
    //防御塔攻击力
    private int defenseValue;
    //防御塔防御值
    private int size;

    public DefenseTower(int x, int y, int aggressivity, int defenseValue, int size) {
        this.x = x;
        this.y = y;
        this.aggressivity = aggressivity;
        this.defenseValue = defenseValue;
        this.size = size;
    }
}