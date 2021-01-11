package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.mapper.test.TestMapper;
import com.example.demo.pojo.test.Test1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: hanDa
 * @Date: 2020/9/11 15:29
 * @Version:1.0
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class BigDecimalInTKMybatisTest {
    @Autowired
    TestMapper testMapper;
    public static String toJson(Object object){
        return JSON.toJSONString(object, new SerializerFeature[]{
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.UseISO8601DateFormat
        });
    }

    @Test
    public void bigdecimalTest(){
        Test1 test1 = new Test1();
        test1.setName("ss");
        test1.setId(null);
        testMapper.select(test1);
    }
}