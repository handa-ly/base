package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hanDa
 * @Date: 2021/4/15 15:00
 * @Version:1.0
 * @Description:
 */
@RestController
@RequestMapping("/timeout")
public class ConnectTimeOutController {

    @GetMapping("")
    public String test(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "timeout";
    }
}