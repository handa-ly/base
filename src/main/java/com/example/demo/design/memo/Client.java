package com.example.demo.design.memo;
/**
    *@ClassName: Client
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/6/22 11:38
    */
    
    
public class Client {
  public static void main(String[] args) {
    Originator originator = new Originator();
    CareTaker careTaker = new CareTaker();
    originator.setStatus1("中国");
    originator.setStatus2("情圣");
    originator.setStatus3("富强");
    System.out.println("======初始化状态======/n"+originator);
    careTaker.setMemento(originator.createMemento());
    //修改状态
    originator.setStatus1("1") ;
    originator.setStatus2("2");
    originator.setStatus3("3");
    System.out.println("=======修改后状态====/n"+originator);
    originator.restoreMemento(careTaker.getMemento());
    System.out.println("=======恢复后状态=====/n"+originator);
  }




}
