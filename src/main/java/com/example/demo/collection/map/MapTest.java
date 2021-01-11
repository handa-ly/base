package com.example.demo.collection.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * @description:
 * @author: handa
 * @time: 2020/1/20 17:11
 */
public class MapTest {

  public static void main(String[] args) {
    /* ==================   函数式接口的使用和for循环执行顺序测试========================================================================================*/
    Map<Object, Object> map = new HashMap<>();
    map.put(1, 2);
    AtomicInteger i = new AtomicInteger();
    String ss = "ss";
    for (System.out.println("位置1:" + i.getAndIncrement());
        isTrue(
            () -> {
              System.out.println("位置2:" + i.getAndIncrement() + ss);
              return true;
            });
        System.out.println("位置3:" + i.getAndIncrement())) {
      System.out.println("位置4:" + i.getAndIncrement());
      if (i.get() > 10) return;
    }
  }

  static boolean isTrue(Supplier<Boolean> supplier) {
    return supplier.get();
  }
}
