package com.example.demo.aop.aspectJ;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: da.Han
 * @createDate: 2020/7/15
 * @version: 1.0
 */
@Component
public class TargetObject {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getObject(){
    return this.name;
  }
}
