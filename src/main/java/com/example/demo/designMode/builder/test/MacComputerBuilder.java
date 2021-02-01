package com.example.demo.designMode.builder.test;

/**
 * @Author: hanDa
 * @Date: 2021/2/1 11:23
 * @Version:1.0
 * @Description:
 */
public class MacComputerBuilder extends ComputerBuilder{
    private Computer computer;
    public MacComputerBuilder(String cpu, String ram) {
        computer = new Computer(cpu, ram);
    }
    @Override
    public void setUsbCount() {
        computer.setUsbCount(2);
    }
    @Override
    public void setKeyboard() {
        computer.setKeyboard("苹果键盘");
    }
    @Override
    public void setDisplay() {
        computer.setDisplay("苹果显示器");
    }
    @Override
    public Computer getComputer() {
        return computer;
    }
}