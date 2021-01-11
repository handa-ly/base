package com.example.demo.service.quartz.impl;

import org.apache.ibatis.cursor.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.pojo.quartz.QuartzJobLog;
import com.example.demo.mapper.quartz.QuartzJobLogMapper;
import com.example.demo.service.BaseServiceImpl;
import com.example.demo.service.quartz.QuartzJobLogService;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.WeakHashMap;
import java.util.function.Consumer;

@Service
public class QuartzJobLogServiceImpl extends BaseServiceImpl<QuartzJobLog> implements QuartzJobLogService {

	private final QuartzJobLogMapper quartzJobLogMapper;

    @Autowired
    public QuartzJobLogServiceImpl(QuartzJobLogMapper quartzJobLogMapper) {
        super(quartzJobLogMapper);
        this.quartzJobLogMapper = quartzJobLogMapper;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Cursor<QuartzJobLog> limit(Integer limit) {
        try (Cursor<QuartzJobLog> quartzJobLogs = quartzJobLogMapper.limit(limit)) {
            quartzJobLogs.forEach(System.out::println);
            Integer count = quartzJobLogs.getCurrentIndex();
            quartzJobLogs.isConsumed();
            quartzJobLogs.isOpen();
            quartzJobLogs.isConsumed();
            Spliterator spliterator = quartzJobLogs.spliterator();
            spliterator.characteristics();
            spliterator.estimateSize();
            spliterator.forEachRemaining(System.out::println);
            spliterator.getExactSizeIfKnown();
            spliterator.getComparator();
            spliterator.hasCharacteristics(1);
            spliterator.tryAdvance(o -> System.out.println(o));
            spliterator.trySplit();
            Iterator<QuartzJobLog> iterator = quartzJobLogs.iterator();
            while (iterator.hasNext()) {
                QuartzJobLog quartzJobLog = iterator.next();
            }
            return quartzJobLogs;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}