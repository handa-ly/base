package com.example.demo.qurtz;

import com.example.demo.mapper.quartz.QuartzJobLogMapper;
import com.example.demo.pojo.quartz.QuartzJobLog;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
    *@ClassName: HelloJob
    *@Description: TODO
    *@Author: handa
    *@Date: 2020/6/3 16:07
    */
    
@Component
@DisallowConcurrentExecution
public class HelloJob implements Job {

  @Value("${server.port}")
  private int serverPort;

  @Autowired
  private QuartzJobLogMapper quartzJobLogMapper;

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    InetAddress address = null;
    try {
      address = InetAddress.getLocalHost();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    QuartzJobLog quartzJobLog = new QuartzJobLog();
    quartzJobLog.setHost(address.getHostAddress());
    quartzJobLog.setPort(String.valueOf(this.serverPort));
    quartzJobLog.init();
    quartzJobLogMapper.insert(quartzJobLog);
    System.out.println("=============="+quartzJobLog.toString()+"======================");
  }
}
