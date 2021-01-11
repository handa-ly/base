package com.example.demo.lambda;

import com.example.demo.optional.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Author: hanDa
 * @Date: 2020/10/26 18:54
 * @Version:1.0
 * @Description:
 */
public class LambdaTest {

    private static  String ss = "ss";
    static String sss = "ss";
    public void test(LambdaInterface lambdaInterface){
        lambdaInterface.sayHello(ss);
    }
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Student student = new Student("SS",22);
        Student student1 = new Student("SSs",22);
        students.add(student);
        students.add(student1);
        System.out.println(students.stream().filter(distinctByKey(student2->student2.getName().concat(";").concat(student2.getAge()+""))).collect(Collectors.toList()).toString());

    }
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}