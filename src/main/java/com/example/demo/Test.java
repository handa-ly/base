package com.example.demo;

import org.apache.commons.lang3.StringUtils;

import javax.xml.crypto.Data;
import java.util.*;
import java.util.stream.Collectors;

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

        public Stu() {
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

    private static final String START_SPACES_REGEX = "^\\s+";
    public static final String EMPTY_STRING = "";
    public static String trimStart(String value) {
        return value.replaceFirst(START_SPACES_REGEX, EMPTY_STRING);
    }
    public static void main(String[] args) throws CloneNotSupportedException {

/*String sss = "\uFEFFHDRSO000000052Performing Right Society                     01.102016062012420320160620";
        System.out.println(sss.substring(1));*/
        System.out.println(0x100000);
    }

}