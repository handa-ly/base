package com.example.demo.service.quartz;


import com.example.demo.pojo.quartz.QuartzJobLog;
import com.example.demo.service.BaseService;

public interface QuartzJobLogService extends BaseService<QuartzJobLog> {
    QuartzJobLog selectAll();

}