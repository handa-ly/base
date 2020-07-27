package com.example.demo.charset;

import java.io.UnsupportedEncodingException;

/**
 * @description:
 * @author: da.Han
 * @createDate: 2020/7/20
 * @version: 1.0
 */
public class BtyeTest {
  public static void main(String[] args) throws UnsupportedEncodingException {
    String encode = "utf-8";
    String ss = "钱";
    String s1 = "a";
    String s2 = "A";
    String s3 = ",";
    String s4 = ":";
    int s5 = 0x00D6;
    char s6 = (char) s5;
    int s7 = 'a';
    System.out.println(s5);
  }
}
