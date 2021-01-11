package com.example.demo.mybatis.mapping;

import com.example.demo.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;

/**
 * @ClassName: UserMapper
 * @Description: TODO
 * @Author: handa
 * @Date: 2020/5/6 16:41
 */

public interface UserMapper {
  User selectUserById(Long id);

  @Select("select * from user_info limit #{limit}")
  Cursor<User> scan(@Param("limit") Integer limit);
}
