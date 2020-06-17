package com.example.demo.aspect.target;

import org.springframework.stereotype.Service;

/**
    *@ClassName: UserService
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/4/23 11:51
    */
    
@Service
public class UserService implements IUserService {
    @Override
    public String addUser(String name, int age) {
        //省略诸如操作数据库等复杂的逻辑操作
        System.out.println("add user "+ name +" successfully");
        return "add user success!";
    }

    @Override
    public String deleteUser(String name) {
        //省略诸如操作数据库等复杂的逻辑操作
        System.out.println("deleted one user named " + name);
//        throw new RuntimeException("这是特意抛出的异常信息！");
        return "delete success!";
    }
}
