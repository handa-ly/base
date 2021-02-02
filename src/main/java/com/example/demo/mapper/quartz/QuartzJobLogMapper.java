package com.example.demo.mapper.quartz;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.stereotype.Repository;
import com.example.demo.base.BaseMapper;
import com.example.demo.pojo.quartz.QuartzJobLog;

import java.util.Map;

@Repository
public interface QuartzJobLogMapper extends BaseMapper<QuartzJobLog> {

    @Select("select * from quartz_job_log limit #{limit}")
    Cursor<QuartzJobLog> limit(@Param("limit") Integer limit);

    @Select("select * from quartz_job_log where host =  '${host}' and port = '${port}' and id = '${6}'")
    Cursor<QuartzJobLog> getList(Map<String,Object> params);

    @Select("${sql}")
    Cursor<QuartzJobLog> getComplexList(@Param("sql") String sql,Map<String,Object> params);
}