package com.example.demo.enums;

public enum EnumTest {
    A(1,"1","11"),
    B(1,"1","11"),
    C(1,"1","11"),
    D(1,"1","11"),
    E(1,"1","11");
    private int a;
    private String b;
    private String c;

    EnumTest(int a, String b, String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static void main(String[] args) {
        EnumTest enumTest = EnumTest.A;
        enumTest.ordinal();
        enumTest.name();
        enumTest.compareTo(EnumTest.B);
        enumTest.equals("A");
        enumTest.getDeclaringClass();
        Enum.valueOf(EnumTest.class,"A");
        System.out.println(enumTest.getA());
        System.out.println(enumTest.getB());
        System.out.println(enumTest.getC());
    }

    public int getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }
}
