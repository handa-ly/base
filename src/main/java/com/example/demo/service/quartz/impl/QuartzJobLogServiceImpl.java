package com.example.demo.service.quartz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.pojo.quartz.QuartzJobLog;
import com.example.demo.mapper.quartz.QuartzJobLogMapper;
import com.example.demo.service.BaseServiceImpl;
import com.example.demo.service.quartz.QuartzJobLogService;
import tk.mybatis.mapper.entity.Example;

@Service
public class QuartzJobLogServiceImpl extends BaseServiceImpl<QuartzJobLog> implements QuartzJobLogService {

	private final QuartzJobLogMapper quartzJobLogMapper;

    @Autowired
    public QuartzJobLogServiceImpl(QuartzJobLogMapper quartzJobLogMapper) {
        super(quartzJobLogMapper);
        this.quartzJobLogMapper = quartzJobLogMapper;
    }

    @Override
    public QuartzJobLog selectAll() {
        Example example = new Example(QuartzJobLog.class);
        Example.Criteria criteria =example.createCriteria();
        example.selectProperties("host");
        quartzJobLogMapper.selectByExample(example);
        return new QuartzJobLog();
    }
}