package com.itheima.factory;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.FactoryBean;

//FactoryBean创建对象
public class UserDaoFactoryBean implements FactoryBean<UserDao> {
    //代替原始实例工厂中创建对象的方法

    public UserDao getObject() throws Exception {
        return new UserDaoImpl();
    }

    public Class<?> getObjectType() {
        return UserDao.class;
    }

    public boolean isSingleton() {
        // 设置单例模式.
        return true;
    }
}

public class Usertest implements FactoryBean<Usertest>{
    public Usertest getObject() throws Exception {
        return new Usertest;
    }

    public Class<?> getObjectType() {
        return Usertest.class;
    }

    public boolean isSingleton() {
        retun false
    }
}