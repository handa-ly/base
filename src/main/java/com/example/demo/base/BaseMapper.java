package com.example.demo.base;

import com.example.demo.cusMapper.UpdateBatchByPrimaryKeySelectiveMapper;
import java.util.List;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 基Mapper （自定义接口方法需要编写SQL）
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, IdsMapper<T>,
    UpdateBatchByPrimaryKeySelectiveMapper<T> {

    /**
     * 获取有效记录（通过主键）
     * @param id 主键ID
     * @return T
     */
    T selectEffective(Long id);

    /**
     * 获取有效记录列表（通过主键列表）
     * @param ids 主键ID列表
     * @return List<T>
     */
    List<T> selectEffective(List<Long> ids);

    /**
     * 获取有效记录列表（通过参数不为空属性）
     * @param record 查询参数对象
     * @return List<T>
     */
    List<T> selectEffective(T record);

    /**
     * 获取全部有效记录列表
     * @return List<T>
     */
    List<T> selectEffective();

    /**
     * 失效
     * @param id 主键ID
     * @return record Num
     */
    int invalid(Long id);

    /**
     * 批量失效
     * @param ids 主键ID列表
     * @return record Num
     */
    int invalid(List<Long> ids);

    /**
     * 生效
     * @param id 主键ID
     * @return record Num
     */
    int effective(Long id);

    /**
     * 批量生效
     * @param ids 主键ID列表
     * @return record Num
     */
    int effective(List<Long> ids);

    /**
     * 全部生效
     * @return record Num
     */
    int effective();
}
