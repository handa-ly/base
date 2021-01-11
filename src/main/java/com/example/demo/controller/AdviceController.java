package com.example.demo.controller;

import com.example.demo.pojo.test.Test1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: hanDa
 * @Date: 2020/9/25 10:36
 * @Version:1.0
 * @Description:
 */
@Slf4j
@RestController
public class AdviceController {

    @GetMapping("/getAttribute")
    public Test1 getAttribute(@ModelAttribute("global") String globel){
      log.info("global: ", globel);
      return new Test1();
    }

    @GetMapping("/bindDate")
    public void bindDate(Date date){
      log.info("date: {}", new SimpleDateFormat("yyyy-MM-dd").format(date));
    }
    @GetMapping("/exception")
    public void exception(){
        String s = null;
        log.info("error",s.toString());
    }
}