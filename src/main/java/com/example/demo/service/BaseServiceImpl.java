package com.example.demo.service;

import com.example.demo.base.BaseMapper;
import com.github.pagehelper.PageHelper;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.entity.Example;

public class BaseServiceImpl<T> implements BaseService<T> {

    private BaseMapper<T> baseMapper;

    public BaseServiceImpl(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public Integer count(T record) {
        return baseMapper.selectCount(record);
    }

    @Override
    public T getById(Long id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public T getEffectiveById(Long id) {
        return baseMapper.selectEffective(id);
    }

    @Override
    public List<T> listByIds(List<Long> ids) {
        return baseMapper.selectByIds(ids.toString());
    }

    @Override
    public List<T> listEffectiveByIds(List<Long> ids) {
        return baseMapper.selectEffective(ids);
    }

    @Override
    public List<T> listAll() {
        return baseMapper.selectAll();
    }

    @Override
    public List<T> listEffectiveAll() {
        return baseMapper.selectEffective();
    }

    @Override
    public List<T> listAllWithPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return baseMapper.selectAll();
    }

    @Override
    public List<T> listEffectiveAllWithPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return baseMapper.selectEffective();
    }

    @Override
    public List<T> list(T record) {
        return baseMapper.select(record);
    }

    @Override
    public List<T> listEffective(T record) {
        return baseMapper.selectEffective(record);
    }

    @Override
    public List<T> listWithPage(T record, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return baseMapper.select(record);
    }

    @Override
    public List<T> listEffectiveWithPage(T record, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return baseMapper.selectEffective(record);
    }

    @Override
    public Integer add(T record) {
        return baseMapper.insert(record);
    }

    @Override
    public Integer addSelective(T record) {
        return baseMapper.insertSelective(record);
    }

    @Override
    public Integer addList(List<T> records) {
        if (records == null || records.isEmpty()) {
            return 0;
        }
        return baseMapper.insertList(records);
    }

    @Override
    public Integer update(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }

    @Override
    public Integer updateSelective(T record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer updateByExampleSelective(T record, Example example) {
        return baseMapper.updateByExampleSelective(record, example);
    }

    @Override
    public Integer delete(Long id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer delete(List<Long> ids) {
        return baseMapper.deleteByIds(StringUtils.strip(ids.toString(),"[]"));
    }

    @Override
    public Integer deleteByExample(Example example) {
        return baseMapper.deleteByExample(example);
    }

    @Override
    public Integer invalid(Long id) {
        return baseMapper.invalid(id);
    }

    @Override
    public Integer invalid(List<Long> ids) {
        return baseMapper.invalid(ids);
    }

    @Override
    public Integer restore(Long id) {
        return baseMapper.effective(id);
    }

    @Override
    public Integer restore(List<Long> ids) {
        return baseMapper.effective(ids);
    }

    @Override
    public Integer updateBatchByPrimaryKeySelective(List<T> recordList) {
        return baseMapper.updateBatchByPrimaryKeySelective(recordList);
    }
}
