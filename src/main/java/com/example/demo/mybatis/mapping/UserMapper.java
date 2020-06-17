package com.example.demo.mybatis.mapping;

import com.example.demo.mybatis.pojo.User;

/**
 * @ClassName: UserMapper
 * @Description: TODO
 * @Author: handa
 * @Date: 2020/5/6 16:41
 */

public interface UserMapper {
  User selectUserById(Long id);
}
