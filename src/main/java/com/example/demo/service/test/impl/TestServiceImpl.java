package com.example.demo.service.test.impl;

import com.example.demo.mapper.test.TestMapper;
import com.example.demo.pojo.test.Test1;
import com.example.demo.result.MustException;
import com.example.demo.service.BaseServiceImpl;
import com.example.demo.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestServiceImpl extends BaseServiceImpl<Test1> implements TestService {

	private final TestMapper testMapper;

    @Autowired
    public TestServiceImpl(TestMapper testMapper) {
        super(testMapper);
        this.testMapper = testMapper;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void insert() {
        try{
            for (int i =0;i<4;i++){
                Test1 test =new Test1();
                if(i ==3){
                    String ss= null;
                    ss.toCharArray();
                }
                testMapper.insert(test);
            }
        } catch (Exception e){
            throw new MustException();
        }
    }
}