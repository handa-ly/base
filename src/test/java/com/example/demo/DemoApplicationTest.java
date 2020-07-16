package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.aop.aspectJ.TargetObject;
import com.example.demo.stream.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @ClassName: DemoApplicationTest
 * @Description: TODO
 * @Author: handa
 * @Date: 2020/4/24 16:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
//@EnableAspectJAutoProxy
public class DemoApplicationTest {

  public static void main(String[] args) {
    String ss = "{\"ss\":\"ss\",\"ss\":\"ss\"}";
    System.out.println(JSON.parse(ss).toString());
  }
  @Autowired
  TargetObject targetObject;
  @Test
  public void collectionToMap() {
    List<Student> list = getStudentList();
    list.add(new Student(1, "xixi", 12));
//     java.lang.IllegalStateException: Duplicate key Student(id=1, name=lisi, age=5) 有异常
//        Map<Integer, Student> collect = list.stream().collect(Collectors.toMap(Student::getId, Function.identity()));

    //源码给出的解决办法 key 为ID value为name 对于key相同的value 使用逗号拼接,适用于一个联系人同名，电话不同
 /*   Map<Integer, String> collect_ = list.stream().collect(
        Collectors.toMap(Student::getId, Student::getName, (s, a) -> s + ", " + a));
    for (Integer key : collect_.keySet()) {
      System.out.println("key: " + key + "====value: " + collect_.get(key));
    }
    System.out.println("'==================='");

    Map<Integer, Student> collect_1 = list.stream().collect(Collectors.toMap(Student::getId, Function
        .identity(), (a, b) -> a.equals(b) && a.hashCode() == b.hashCode() ? a : b));
    for (Integer key : collect_1.keySet()) {
      System.out.println("key: " + key + "====value: " + collect_1.get(key));
    }
    System.out.println("'==================='");*/

    // 判断一个对象是否相同equals为true 并且hashcode相同
    Map<Integer, Student> collect_2 = list.stream().collect(Collectors
        .toMap(Student::getId, Function.identity(), (a, b) -> a.getAge() > b.getAge() ? a : b));
    for (Integer key : collect_2.keySet()) {
      System.out.println("key: " + key + "====value: " + collect_2.get(key));
    }
  }

  private List<Student> getStudentList() {
    List<Student> sList = new ArrayList<>();
    sList.add(new Student(1, "lisi", 5));
    sList.add(new Student(2, "wangwu", 7));
    sList.add(new Student(3, "maliu", 9));
    return sList;
  }

  @Test
  public void aspectTest(){
    targetObject.setName("test");
  }
}
