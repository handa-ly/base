package com.example.demo;

import java.math.BigDecimal;
import java.math.BigInteger;
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
        BigDecimal b = new BigDecimal(104233.8);
        BigDecimal c = BigDecimal.ZERO;
        System.out.println(b.add(c));
    }
}