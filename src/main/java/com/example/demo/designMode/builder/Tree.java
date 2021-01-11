package com.example.demo.designMode.builder;

import lombok.Data;

/**
 * @Author: hanDa
 * @Date: 2020/9/15 18:03
 * @Version:1.0
 * @Description:
 */
@Data
public class Tree {
    private String color;  //树颜色
    private int size;  //树尺寸
    private int x;  //树x坐标
    private int y;  //树y坐标

    public Tree(String color, int size, int x, int y) {
        this.color = color;
        this.size = size;
        this.x = x;
        this.y = y;
    }
}