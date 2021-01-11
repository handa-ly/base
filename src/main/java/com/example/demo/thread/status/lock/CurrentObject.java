package com.example.demo.thread.status.lock;

/**
 * @Author: hanDa
 * @Date: 2020/10/9 15:25
 * @Version:1.0
 * @Description: 锁定当前对象的属性，锁定的还是当前对象
 */
public class CurrentObject {
    private CurrentObject1 currentObject1;
    private int count = 1;

    private Integer a = 1;
    private Integer b = 1;

    public void setCurrentObject1(CurrentObject1 currentObject1) {
        this.currentObject1 = currentObject1;
    }

    synchronized void getAmount(String name){
        System.out.println(name+":"+count++);
        currentObject1.getAmount(name);
//        getAmount1();
    }
    synchronized void getAmount1(){
//        amount = 5;
//        getAmount();
    }

    public void getA(String name){
        synchronized (this.a){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getB(name);
        }
    }
    public void getB(String name){
        synchronized (this.b){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name);
            getA(name);
        }
    }
}