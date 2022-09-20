package com.itheima.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.User;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
