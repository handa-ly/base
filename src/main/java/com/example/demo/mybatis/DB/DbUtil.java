package com.example.demo.mybatis.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
    *@ClassName: DbUtil
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/5/6 19:08
    */
    
    
public class DbUtil {
  public static final String URL = "jdbc:mysql://175.24.45.45:3306/quartz?serverTimezone=UTC";
  public static final String USER = "root";
  public static final String PASSWORD = "handa2695170923";

  public static void main(String[] args) throws Exception {
    System.out.println(args.toString());
    //jdbc连接方式
    //1.加载驱动程序
//    Class.forName("com.mysql.jdbc.Driver");
    //2. 获得数据库连接
    Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
    //3.操作数据库，实现增删改查
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT user_name, age FROM user");
    //如果有数据，rs.next()返回true
    while(rs.next()){
      System.out.println(rs.getString("user_name")+" 年龄："+rs.getInt("age"));
    }
  }
}
