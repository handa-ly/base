package com.example.demo.controller;

import com.example.demo.result.MustException;
import com.example.demo.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hanDa
 * @Date: 2020/8/29 15:18
 * @Version:1.0
 * @Description:
 */
@RestController
@RequestMapping("/transcationRollBackTestController")
public class TranscationRollBackTestController {
    @Autowired
    private TestService testService;

    @PostMapping("/test")
    public void test(){
        try{
            testService.insert();
        }catch (Exception e){
            System.out.println(e instanceof MustException);
            e.printStackTrace();
        }
    }
}