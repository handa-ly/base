package com.example.demo.mapper.test;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.example.demo.base.BaseMapper;
import com.example.demo.pojo.test.Test1;

import java.util.List;
import java.util.Map;

@Repository
public interface TestMapper extends BaseMapper<Test1> {

    Test1 getTest();

    @Select({"select * from test"})
    List<Map<String,Object>> getMap();

}