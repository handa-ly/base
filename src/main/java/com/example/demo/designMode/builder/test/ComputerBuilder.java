package com.example.demo.designMode.builder.test;

/**
 * @Author: hanDa
 * @Date: 2021/2/1 11:23
 * @Version:1.0
 * @Description:
 */
public abstract class ComputerBuilder {
    public abstract void setUsbCount();
    public abstract void setKeyboard();
    public abstract void setDisplay();

    public abstract Computer getComputer();
}