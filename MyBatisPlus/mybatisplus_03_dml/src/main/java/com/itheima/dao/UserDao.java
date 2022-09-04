package com.itheima.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
    // 自定义一个保存.
    @Insert("insert into tbl_user(name,pwd,age,tel)values(#{Name},#{Passwd},#{Age},#{Tel})")
    void DefineSave(String Name, String Passwd, int Age, String Tel);
}
