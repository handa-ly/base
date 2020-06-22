package com.example.demo.design.memo;

import lombok.Data;

/**
    *@ClassName: Originator
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/6/22 11:09
    */
@Data
public class Originator implements Cloneable{
  private String status1;
  private String status2;
  private String status3;

  public Memento createMemento(){
    return new Memento(BeanUtils.backPro(this));
  }
  public void restoreMemento(Memento _memento){
    BeanUtils.restorePro(this,_memento.getStateMap());
  }
}
