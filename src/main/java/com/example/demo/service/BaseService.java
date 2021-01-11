package com.example.demo.service;

import java.util.List;
import tk.mybatis.mapper.entity.Example;

/**
 * 通用Service接口
 */
public interface BaseService<T> {

    /**
     * 根据不为空属性统计
     *
     * @param record 查询参数对象
     * @return int
     */
    Integer count(T record);

    /**
     * 根据ID查询
     *
     * @param id 主键ID
     * @return T
     */
    T getById(Long id);

    /**
     * 根据ID查询(有效)
     *
     * @param id 主键ID
     * @return T
     */
    T getEffectiveById(Long id);

    /**
     * 根据ID列表查询
     *
     * @param ids 主键ID列表
     * @return List<T>
     */
    List<T> listByIds(List<Long> ids);

    /**
     * 根据ID列表查询（有效）
     *
     * @param ids 主键ID列表
     * @return List<T>
     */
    List<T> listEffectiveByIds(List<Long> ids);

    /**
     * 查询全部
     *
     * @return List<T>
     */
    List<T> listAll();

    /**
     * 查询全部（有效）
     *
     * @return List<T>
     */
    List<T> listEffectiveAll();

    /**
     * 分页查询全部
     *
     * @param pageNum  页序号
     * @param pageSize 页大小
     * @return List<T>
     */
    List<T> listAllWithPage(Integer pageNum, Integer pageSize);

    /**
     * 分页查询全部（有效）
     *
     * @param pageNum  页序号
     * @param pageSize 页大小
     * @return List<T>
     */
    List<T> listEffectiveAllWithPage(Integer pageNum, Integer pageSize);

    /**
     * 根据实体不为空属性进行查询
     *
     * @param record 查询参数对象
     * @return List<T>
     */
    List<T> list(T record);

    /**
     * 根据实体不为空属性进行查询（有效）
     *
     * @param record 查询参数对象
     * @return List<T>
     */
    List<T> listEffective(T record);

    /**
     * 根据实体不为空属性进行分页查询
     *
     * @param record 查询参数对象
     * @param pageNum  页序号
     * @param pageSize 页大小
     * @return List<T>
     */
    List<T> listWithPage(T record, Integer pageNum, Integer pageSize);

    /**
     * 根据实体不为空属性进行分页查询（有效）
     *
     * @param record 查询参数对象
     * @param pageNum  页序号
     * @param pageSize 页大小
     * @return List<T>
     */
    List<T> listEffectiveWithPage(T record, Integer pageNum, Integer pageSize);

    /**
     * 保存
     * @param record 对象
     * @return int
     */
    Integer add(T record);

    /**
     * 保存不为空属性
     * @param record 对象
     * @return int
     */
    Integer addSelective(T record);

    /**
     * 批量插入
     * @param records 对象列表
     * @return int
     */
    Integer addList(List<T> records);

    /**
     * 更新
     * @param record 对象
     * @return int
     */
    Integer update(T record);

    /**
     * 更新不为空属性
     * @param record 对象
     * @return int
     */
    Integer updateSelective(T record);


    Integer updateByExampleSelective(T record, Example example);

    /**
     * 删除
     *
     * @param id 主键ID
     * @return record Num
     */
    Integer delete(Long id);

    /**
     * 批量删除
     *
     * @param ids 主键ID列表
     * @return record Num
     */
    Integer delete(List<Long> ids);


    Integer deleteByExample(Example example);

    /**
     * 失效
     * @param id 主键ID
     * @return record Num
     */
    Integer invalid(Long id);

    /**
     * 批量失效
     * @param ids 主键ID列表
     * @return record Num
     */
    Integer invalid(List<Long> ids);

    /**
     * 生效
     *
     * @param id 主键ID
     * @return record Num
     */
    Integer restore(Long id);

    /**
     * 批量生效
     *
     * @param ids 主键ID列表
     * @return record Num
     */
    Integer restore(List<Long> ids);

    Integer updateBatchByPrimaryKeySelective(List<T> recordList);

}
