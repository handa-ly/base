package com.example.demo.aop;

import java.util.Date;

/**
 * @ClassName: Logger @Description: TODO @Author: handa @Date: 2020/7/9 17:59
 */
public class Logger {

  /**
   * 7 * 根据等级记录日志 8 * @param level 9 * @param context 10
   */
  public static void logging(Level level, String context) {

    if (level.equals(Level.INFO)) {
      System.out.println(new Date().toLocaleString() + " " + context);
    }
    if (level.equals(Level.DEBUGE)) {
      System.err.println(new Date() + " " + context);
    }
  }
}
