package com.example.demo.designMode.builder;

import lombok.Data;

/**
 * @Author: hanDa
 * @Date: 2020/9/15 18:02
 * @Version:1.0
 * @Description:
 */
@Data
public class Road {
    //路的尺寸
    private int length;
    //路的长度
    private int x;
    //路的x坐标
    private int y;
    //路的y坐标
    private int width;

    public Road(int length, int x, int y, int width) {
        this.length = length;
        this.x = x;
        this.y = y;
        this.width = width;
    }
}