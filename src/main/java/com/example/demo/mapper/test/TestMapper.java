package com.example.demo.mapper.test;

import org.springframework.stereotype.Repository;
import com.example.demo.base.BaseMapper;
import com.example.demo.pojo.test.Test1;

@Repository
public interface TestMapper extends BaseMapper<Test1> {

    Test1 getTest();

}