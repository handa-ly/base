package com.example.demo.designMode.builder.test;

/**
 * @Author: hanDa
 * @Date: 2021/2/1 11:25
 * @Version:1.0
 * @Description:
 */
public class ComputerDirector {
    public void makeComputer(ComputerBuilder builder){
        builder.setUsbCount();
        builder.setDisplay();
        builder.setKeyboard();
    }

    public static void main(String[] args) {
        ComputerDirector director=new ComputerDirector();//1
        ComputerBuilder builder=new MacComputerBuilder("I5处理器","三星125");//2
        director.makeComputer(builder);//3
        Computer macComputer=builder.getComputer();//4
        System.out.println("mac computer:"+macComputer.toString());

        ComputerBuilder lenovoBuilder=new LenovoComputerBuilder("I7处理器","海力士222");
        director.makeComputer(lenovoBuilder);
        Computer lenovoComputer=lenovoBuilder.getComputer();
        System.out.println("lenovo computer:"+lenovoComputer.toString());
    }
}