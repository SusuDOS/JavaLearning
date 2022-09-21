# Java学习笔记

这是个人学下java后端的学习笔记，只要保存java技术中的代码演示，环境搭建，服务启动等基本信息，部分知识点可能整理进入综合性部分。


## Java基础

- 继承与多态
- IO流
- 网络编程
- 多线程和线程池
- 反射

## Java Web&SQL

- DDL(结构操作)
- DML(数据操作)
- DQL(数据查询)

### 多表联合查询

- 查询语句
```bash
1.完整的查询语句的执行优先级:select * from stu where score='100' group by gender having count(*)  > 2 order by score desc limit 0,3;
```
- 隐式内联

```sql
SELECT
    emp. NAME,
    emp.gender,
    dept.dname
FROM
    emp,
    dept
WHERE
    emp.dep_id = dept.did;
```

- 显示内联

```sql
-- inner可以忽略
select * from emp inner join dept on emp.dep_id = dept.did;
```

- 左外联(显示)

```sql
select * from emp left join dept on emp.dep_id = dept.did;
```

- 右外联(显示)

```sql
select * from emp right join dept on emp.dep_id = dept.did;
```

- DCL(账号控制)

## Git操作

- 库初始化

- 上传三连

- 分支切换

- `idea` 完成上述操作

## Spring

非常原始的方式去创建和管理Bean对象....核心知识点可能就环绕通知和事物管理非常有意义。

- Bean

- Bean的创建

- AOP-5种通知类型(核心环绕通知)

- 事物管理

## SpringMVC

包含SpringMVC及其整合MybatisCode的完整代码，包括每一个案例！

- bean加载的范围

- web传入参数的响应

- Resful代码风格

- 自定义访问函数异常

- 代码合并...

- 一种程序前置的验证策略，给访问者添加权限！

- 最后注意config包下的各种配置的互相导入！

### springmvc_11_page为内容最完整

- bean加载的范围

- web传入参数的响应

- Resful代码风格

- 自定义访问函数异常

- 代码合并...


### springmvc_12_interceptor加拦截器

- 一种程序前置的验证策略，给访问者添加权限！

### Mybatis

对数据库进行操作，是做网站服务极为重要的组成部分。为SSM三大框架的其中一部分...

具体可以查看 `springmvc_11_page` 文件夹代码逻辑。

## Maven

- 依赖的传递、聚合、管理.

- POM读取属性到POM和properties.

- 自建maven私服

## Spring Boot

- 对starter讲解在pinda-authority系列的开篇内容较多。

### 知识点

![](./SpringBoot视频.png)

- properties,yml,yaml优先级

- 从yaml中读取数据的三种方式

- 多环境开发配置

- 多环境命令行启动参数

- SpringBoot整合

- 图书馆整合-SpringBoot版

- AOP异常捕获

### 实现功能

- 完成一个图书馆系统页面RestFul增删查改基本功能，以及AOP通知类实现业务代码出错处理，以及事务管理，同成功，同失败。

- 核心注解 `@RestControllerAdvice`。

## MybatisPlus

演示MyBatisPlus增删查改数据库!

### 使用Mybatisplus对数据库进行增删查改

- 开启mp的日志(输出到控制台)

- Mybatisplus查询语法

- 分页拦截器(解决查看具体的查询条数问题)

- 范围查询(继承方式解决)

- mybatisplus代码生成器

- 表与实体映射关系(pom配置演示在03)

```java
@Data
//设置表名映射关系
//@TableName("tbl_user")，可以不写，因为配置了全局前置
public class User {
    //设置主键生成策略
    // @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    @TableField(value = "pwd", select = false)
    private String password;
    private Integer age;
    private String tel;
    @TableField(exist = false)
    private Integer online;
    //逻辑删除字段，标记当前记录是否被删除
    // @TableLogic(value = "0" ,delval = "1")
    private Integer deleted;
    @Version
    private Integer version;
}
```

- 继承与自定义dao方法

```java
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
```


# 中间件与服务框架

此处的知识量开始扩展与各种技术融合，遗憾的是，并没有展示一个项目需要同时使用到RabbitMQ+Redis+微服务架构。不过Redis部分很有意思有较为良好的展示,springcloud中有微服务保护，分布式事务，分布式缓存高级缓存，MQ集群等较新技术但并没有使用到一个具体的项目上。

## SpringCloud微服务

内容过多...没有使用到一个具体的项目上，暂定后续处理,nacos配置以及在Springboot中的配置信息需要留意，容量较大。

- 微服务保护

- 分布式事务

- 分布式缓存高级缓存

- MQ集群和确认机制

## Dubbo分布式

- ZooKeeper注册中心和Dubbo-admin.

- 使用Dubbo-Admin测试service.

- @Reference服务注册在本地调用.

- 关于dubbo使用可以子文件夹资料详解.

## RabbitMQ消息队列

<font color=red>当前未找到使用该技术的项目,需要项目支撑以便有效更新...</font>

## Redis&框架调用Redis

### Redis实战

内容为黑马点评(hm-dianping)，主要使用的技术涉及后端的具体，前端的调用配合。使用了Redis缓存，没有使用SpringCloud，没有使用RabbitMQ消息队列。

- 01.短信登录
- 02.商户查询缓存
- 03.优惠券秒杀
- 04.Feed_GEO_BitMap_HyperLogLog

### Redis高级
- 01-分布式缓存
- 02-多级缓存
- 03-Redis最佳实践

### Redis原理

- 主要是操作Redis的函数介绍

## 编程小Trip

- # 常用快捷键

```
# 快速遍历
fori

# 查看接口继承关系
ctrl+h

# 查看某类的方法+变量
ctrl+F3 或者 Alt+7/6

# Visual Code多端口测试
json中添加如下：
"vmArgs":"-Dserver.port=8081"

# 快速补全变量名
.var 或者ctrl+alt+v

#查看方法参数
Ctrl+p
```

