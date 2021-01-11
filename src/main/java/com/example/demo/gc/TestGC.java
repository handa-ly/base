package com.example.demo.gc;

/**
 * @Author: hanDa
 * @Date: 2020/11/24 10:58
 * @Version:1.0
 * @Description:
 */
public class TestGC {
    private Object instance;
    public TestGC() {
        byte[] m = new byte[20*1024*1024];
    }
    public static void main(String[] args) {
        TestGC a1 = new TestGC();
        TestGC a2 = new TestGC();
        a1.instance=a2;
        a2.instance=a1;

        a1=null;
        a2=null;
        System.gc();
        //parallel 默认采用的垃圾回收器
    }
}