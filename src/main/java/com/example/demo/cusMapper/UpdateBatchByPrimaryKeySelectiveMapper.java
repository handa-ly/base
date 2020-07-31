package com.example.demo.cusMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * @ClassName: UpdateBatchByPrimaryKeySelectiveMapper
 * @Description: TODO
 * @Author: handa
 * @Date: 2020/5/25 11:58
 */

@RegisterMapper
public interface UpdateBatchByPrimaryKeySelectiveMapper<T> {
    @UpdateProvider(type = BatchUpdateByPrimaryKeyProvider.class, method = "dynamicSQL")
    int updateBatchByPrimaryKeySelective(List<T> recordList);


    @UpdateProvider(type = BatchUpdateByPrimaryKeyProvider.class, method = "dynamicSQL")
    Map<String,Object> getTableDetail();

   @Select("${sql}")
    Map<String, Object> selectBySQL(@Param("sql") String sql);
}
