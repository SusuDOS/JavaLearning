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


## git操作

## Spring

## SpringMVC

## Maven

## Mybatis&MybatisPlus

## Spring Boot整合

## SpringCloud微服务

## Dubbo分布式

## Redis&框架调用Redis

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

