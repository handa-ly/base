package com.example.demo.cusMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;
import java.util.Map;

/**
 * @Author handa
 * @Version  1.0
 * @Description
 * @Date 2020/7/31 10:20
 */
@RegisterMapper
public interface OriginalSelectiveMapper {
   @Select("${sql}")
    Map<String, Object> selectBySQL(@Param("sql") String sql);
}
