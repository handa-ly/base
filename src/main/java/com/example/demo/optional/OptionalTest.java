package com.example.demo.optional;

import java.util.Optional;

/**
 * @Author: hanDa
 * @Date: 2020/11/16 20:22
 * @Version:1.0
 * @Description:
 */
public class OptionalTest {
    public static void main(String[] args) {
        Student student = new Student("handa",30);
//        student = null;
//        Optional<Student> optional = Optional.of(student);
        Optional<String> optionalS;
//        System.out.println(optionalS.get());
        optionalS = Optional.empty();
        String optionalss = optionalS.orElse("ss");
//        System.out.println(optionalss);
        Optional optional1 = optionalS.or(()-> Optional.of("ss"));
//        System.out.println(optionalS.get());
//        System.out.println(optional1.get());
//       Optional.of(student).filter(value-> value.getName()=="ss");
//        Optional<Student> student1 = Optional.of(student).or(()-> null);
//        System.out.println(student1.toString());
//        Optional.of(student).orElse(new Student());
        Student student1 = Optional.ofNullable(student).orElse(new Student("SS",11));
//        System.out.println(student1);
        Optional.ofNullable(student).ifPresent(value->{
//            System.out.println(value.toString());
        });
//        System.out.println(Optional.ofNullable(student).equals(Optional.of(student)));
        Optional.ofNullable(student).stream().map(student2 -> student2);
    }

}