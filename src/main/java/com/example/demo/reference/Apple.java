package com.example.demo.reference;

/**
 * @Author: hanDa
 * @Date: 2021/4/1 11:22
 * @Version:1.0
 * @Description:
 */
public class Apple {
    private String name;

    public Apple(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 覆盖finalize，在回收的时候会执行。
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Apple： " + name + " finalize。");
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                '}' + ", hashCode:" + this.hashCode();
    }
}