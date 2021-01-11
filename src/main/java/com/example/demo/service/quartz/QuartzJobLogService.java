package com.example.demo.service.quartz;


import com.example.demo.pojo.quartz.QuartzJobLog;
import com.example.demo.service.BaseService;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;

public interface QuartzJobLogService extends BaseService<QuartzJobLog> {

    Cursor<QuartzJobLog> limit(Integer limit);
}