package com.example.demo.mybatis;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
    *@ClassName: TestMybatis
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/5/6 16:47
    */
@Component
public class TestMybatis implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    //1.获取配置数据文件路径
    String resource = "mybatis.xml";
    //2.加载配置文件configuration：environments数据源，和事务
    //加载到流中
    InputStream inputStream = Resources.getResourceAsStream(resource);
    //3.根据builder构建sqlsessionfactory
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    //4.获取session回话
    SqlSession session = sqlSessionFactory.openSession();
    try {
            /*UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.selectUserById(1);
            System.out.println(user);*/
           /*User user = new User();
           user.setAge(15);
           user.setName("achuan");
           int insert = session.insert("com.wsdsg.spring.boot.analyze.mapper.UserMapper.addOneUser", user);
           session.commit();
           System.out.println(insert);*/
      //5.调用操作sql接口
      Object o = session.selectOne("com.example.demo.mybatis.mapping.UserMapper.selectUserById", 1l);
      System.out.println("我是第一次查询的"+o);
      System.out.println("-------------------------------我是分割线---------------------");
      Object z = session.selectOne("com.example.demo.mybatis.mapping.UserMapper.selectUserById", 1l);
      System.out.println("我是第二次查询的"+z);
    } finally {
      session.close();
    }
  }
}
