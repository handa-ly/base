package com.example.demo.controller;

import java.util.Date;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: QuartzController
 * @Description: TODO
 * @Author: handa
 * @Date: 2020/6/5 17:08
 * */
@RestController
public class QuartzController {

  @Autowired
  private Scheduler scheduler;

  @PostMapping("/addJob")
  public ResponseEntity addJob(){
    try {
      Class<?> clazz = Class.forName("com.example.demo.qurtz.HelloJob");
      Date date = new Date();
      //创建触发器
      Trigger trigger = TriggerBuilder.newTrigger()
          .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ? *").withMisfireHandlingInstructionDoNothing())
          .withDescription("HelloJob定时任务")
          .withIdentity("HelloJobTrigger")
          .build();
      //创建任务
      JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) clazz).withDescription("HelloJob定时任务")
          //添加自定义的JobData，兼容以前sys_job与sys_job_log的关系，并记录日志
          .usingJobData("createTime", date.getTime()).usingJobData("amendTime", date.getTime())
          .usingJobData("remark", "这是一个测试！")
          .build();
      //调度作业
      scheduler.scheduleJob(jobDetail, trigger);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return new ResponseEntity(HttpStatus.OK);

  }
}
