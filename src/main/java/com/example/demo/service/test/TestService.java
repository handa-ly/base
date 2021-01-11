package com.example.demo.service.test;


import com.example.demo.pojo.test.Test1;
import com.example.demo.service.BaseService;

public interface TestService extends BaseService<Test1> {
    void insert();

}