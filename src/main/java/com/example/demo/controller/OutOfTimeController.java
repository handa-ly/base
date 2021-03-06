package com.example.demo.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
    *@ClassName: OutOfTimeController
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/5/22 10:47
    */
    
@RestController
public class OutOfTimeController {
  @Value("${server.port}")
  private int serverPort;
  @GetMapping("/test")
  public String timeOutTest(){
    InetAddress address = null;
    try {
      address = InetAddress.getLocalHost();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return "http://"+address.getHostAddress() +":"+this.serverPort;
  }
}
