package com.example.demo;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: hanDa
 * @Date: 2020/12/16 19:30
 * @Version:1.0
 * @Description:
 */
public class Test {
    public class Stu{
        private String name;
        private Integer age;
        private int height;

        public Stu(String name, Integer age, int height) {
            this.name = name;
            this.age = age;
            this.height = height;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override
        public String toString() {
            return "Stu{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", height=" + height +
                    '}';
        }
    }
    public class CloneTest implements Cloneable{
     private int a = 1;
     private Integer b = new Integer(1);
     private Stu stu;

        public CloneTest(int a, Integer b, Stu stu) {
            this.a = a;
            this.b = b;
            this.stu = stu;
        }

        public CloneTest() {
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            this.a=0;
            this.b=null;
            this.stu=null;
            return super.clone();
        }

        public Stu getStu() {
            return stu;
        }

        public void setStu(Stu stu) {
            this.stu = stu;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "CloneTest{" +
                    "a=" + a +
                    ", b=" + b +
                    ", stu=" + stu +
                    '}';
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Test test = new Test();
        Date startDate = new Date();
        for (int i = 1;i<10000000;i++){
            CloneTest cloneTest = test.new CloneTest();
        }
        System.out.println(System.currentTimeMillis() - startDate.getTime());
        Date startDate1 = new Date();
        CloneTest cloneTest1 = test.new CloneTest();
        for (int i = 1;i<10000000;i++){
            CloneTest cloneTest = (CloneTest) cloneTest1.clone();
        }
        System.out.println(System.currentTimeMillis() - startDate1.getTime());


    }
}