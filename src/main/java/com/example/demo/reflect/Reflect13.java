package com.example.demo.reflect;/**
 * @description:
 * @author: da.Han
 * @createDate: 2020/7/14
 * @version: 1.0
 */
public class Reflect13 {
  public static void main(String[] args) throws Exception {
//      Fruit f = Factory.getInstance("com.mazaiting.Orange");
    Fruit f = Factory.getInstance("com.example.demo.reflect.Apple");
    if (f != null) {
      f.eat();
    }
    /**
     * Apple
     */
  }

}

interface Fruit {
  void eat();
}
class Apple implements Fruit {
  @Override
  public void eat() {
    System.out.println("Apple");
  }
}
class Orange implements Fruit {
  @Override
  public void eat() {
    System.out.println("Orange");
  }
}
class Factory {
  public static Fruit getInstance(String className) {
    Fruit f = null;
    try {
      f = (Fruit) Class.forName(className).getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return f;
  }

}
