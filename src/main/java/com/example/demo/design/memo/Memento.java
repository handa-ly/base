package com.example.demo.design.memo;

import java.util.HashMap;
import lombok.Data;

/**
    *@ClassName: Memento
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/6/22 11:11
    */
    
@Data
public class Memento {

  private HashMap<String,Object> stateMap;

  public Memento(HashMap<String, Object> stateMap) {
    this.stateMap = stateMap;
  }
}
