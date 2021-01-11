package com.example.demo.mapper.quartz;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.stereotype.Repository;
import com.example.demo.base.BaseMapper;
import com.example.demo.pojo.quartz.QuartzJobLog;

@Repository
public interface QuartzJobLogMapper extends BaseMapper<QuartzJobLog> {

    @Select("select * from quartz_job_log limit #{limit}")
    Cursor<QuartzJobLog> limit(@Param("limit") Integer limit);
}