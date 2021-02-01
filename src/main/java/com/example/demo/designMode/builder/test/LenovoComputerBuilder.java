package com.example.demo.designMode.builder.test;

/**
 * @Author: hanDa
 * @Date: 2021/2/1 11:24
 * @Version:1.0
 * @Description:
 */
public class LenovoComputerBuilder extends ComputerBuilder{
    private Computer computer;
    public LenovoComputerBuilder(String cpu, String ram) {
        computer=new Computer(cpu,ram);
    }
    @Override
    public void setUsbCount() {
        computer.setUsbCount(4);
    }
    @Override
    public void setKeyboard() {
        computer.setKeyboard("联想键盘");
    }
    @Override
    public void setDisplay() {
        computer.setDisplay("联想显示器");
    }
    @Override
    public Computer getComputer() {
        return computer;
    }
}