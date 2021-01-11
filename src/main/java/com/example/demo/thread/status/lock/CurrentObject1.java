package com.example.demo.thread.status.lock;

/**
 * @Author: hanDa
 * @Date: 2020/10/9 15:25
 * @Version:1.0
 * @Description:
 */
public class CurrentObject1 {
    private CurrentObject currentObject;
    private int count = 1;

    public CurrentObject1(CurrentObject currentObject) {
        this.currentObject = currentObject;
    }

    public CurrentObject1() {
    }

    synchronized void getAmount(String name){
        System.out.println(name+":"+count++);
        currentObject.getAmount(name);

//        getAmount1();
    }
    synchronized void getAmount1(){
//        amount = 5;
//        getAmount();
    }
}