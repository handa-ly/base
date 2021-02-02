package com.example.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hanDa
 * @Date: 2021/2/1 17:44
 * @Version:1.0
 * @Description:
 */
public class JustTest {
    public static void main(String[] args) {
        String s = "select * from ss where ss = ${tt}";
        Map<String,Object> ss = new HashMap<>();
        ss.put("tt",48);
        System.out.println(s.equals(ss));
    }
}