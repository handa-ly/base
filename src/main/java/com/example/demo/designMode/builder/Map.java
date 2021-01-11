package com.example.demo.designMode.builder;

import lombok.Data;

/**
 * @Author: hanDa
 * @Date: 2020/9/15 18:00
 * @Version:1.0
 * @Description:
 */
@Data
public class Map {
    private DefenseTower defenseTower; //防御塔
    private Road road;  //道路
    private Tree tree;  // 树
    private Monster monster;  // 怪物
    private String music;  //音乐
    private String px;  //像素
}