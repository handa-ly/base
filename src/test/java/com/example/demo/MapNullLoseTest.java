package com.example.demo;

import com.example.demo.mapper.test.TestMapper;
import com.example.demo.service.quartz.QuartzJobLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @Author: hanDa
 * @Date: 2021/3/26 11:34
 * @Version:1.0
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapNullLoseTest {

    @Autowired
    private TestMapper testMapper;

    @Test
    public void test(){
        List<Map<String,Object>> list = testMapper.getMap();

        list.get(0).keySet().stream().forEach(s-> System.out.println(s));
    }
}