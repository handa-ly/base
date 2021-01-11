package com.example.demo.gc;

/**
 * @Author: hanDa
 * @Date: 2021/1/6 14:02
 * @Version:1.0
 * @Description:
 */
public class EdenAllocationTest {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args)
    {
        byte[] allocation1 = new byte[2 * _1MB];
        byte[] allocation2 = new byte[2 * _1MB];
        byte[] allocation3 = new byte[2 * _1MB];
        byte[] allocation4 = new byte[4 * _1MB];
    }
}