# Maven高级部分

## Maven依赖

- 依赖传递

- 依赖聚合

- 依赖管理

- 多环境配置
```bash
# 查看系统环境变量
mvn help:system

# 带环境maven的install
mvn install -P pro_env
```
- 跳过测试
```bash
# maven的闪电符号点击一下

# mvn指令方式
mvn install -D skipTests

# pom插件细粒度控制
```

## Maven自建私服

- Nexus私服的搭建问题...

## 资料位置

- 源自 `2022版SSM框架` 视频配套资源

- 所有资料在文件 `基础框架笔记` 中 ...