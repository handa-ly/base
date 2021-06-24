package com.example.demo.stream;

import com.example.demo.stream.test.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: hanDa
 * @Date: 2021/6/3 15:10
 * @Version:1.0
 * @Description:
 */
public class StreamTest {
    private int value;

    public StreamTest() {
    }

    public static void main(String[] args) {
        //一 流的概念
        //将集合元素看做一种流 使用strem的api对流进行操作 （筛选 排序 聚合）
        //由数组和集合创建 对流的操作有两种 1.中间操作返回流 2.终端操作 流只能进行一次终端操作 产生新的值或者集合
        //特性：stream1.不储存数据  计算得到值 2.延迟执行特性 执行终端操作时才会执行中间操作
//        sort();
        //collect
        collect();
    }

    static List<Person> personList = new ArrayList<Person>();

    static {
        personList.add(new Person("Sherry", 9000, 24, "female", "New York"));
        personList.add(new Person("Tom", 8900, 22, "male", "Washington"));
        personList.add(new Person("Jack", 9000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 8800, 26, "male", "New York"));
        personList.add(new Person("Alisa", 9000, 26, "female", "New York"));

    }

    public static void sort() {
        List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName).collect(Collectors.toList());
        for (int i = 0; i < 100; i++) {
            List<Integer> newList1 = personList.stream().parallel().sorted(Comparator.comparing(Person::getSalary)).map(Person::getSalary).collect(Collectors.toList());
            System.out.println(newList1);

        }
    }

    public static void collect() {
        System.out.println(personList.stream().collect(Collectors.toMap(Person::getName, java.util.function.Function.identity())));
    }

    public static void collect1() {
        List<Person1> list = new ArrayList();
        list.add(new Person1(1, "haha"));
        list.add(new Person1(2, "rere"));
        list.add(new Person1(3, "fefe"));
        Map<Integer, Person1> mapp = list.stream().collect(Collectors.toMap(Person1::getId, java.util.function.Function.identity()));
        System.out.println(mapp);
        System.out.println(mapp.get(1).getName());
        Map<Integer, String> map = list.stream().collect(Collectors.toMap(Person1::getId, Person1::getName));
        System.out.println(map);
    }

    public static class Person1 {

        private Integer id;
        private String name;

        public Person1(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int compareTo() {
            return 0;
        }

        public class test<T> {
            public <T> T maxBy(T t) {
                Objects.requireNonNull(t);
                return t;
            }

            public T re(T t) {
                return t;
            }
        }
    }
}