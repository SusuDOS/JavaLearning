## SpringCloud

- 多nacos部署+Nginx

资源位置:SpringCloud+RabbitMQ+Docker+Redis+搜索+分布式\实用篇

- nacos生成大量日志，预计1s按照8K速度增加，可以直接干掉logs文件夹后本次运行不生成。
- 单台部署多节点：window下正常运行，但是centos7.9下，第一个正常运行，后面几个并不能正常运行，提示地址已经绑定，应该在不同机器上部署应该可以！测试nacos1.4.1以及nacos2.1版本

```bash
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.2.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.2.0 https://maven.apache.org/xsd/settings-1.2.0.xsd">
    <localRepository>/usr/local/apache-maven-3.6.3/MAVEN_RESP</localRepository>
    <mirrors>
        <mirror>
            <id>alimaven</id>
            <name>office maven</name>
            <url>https://repo.maven.apache.org/maven2/</url>
            <mirrorOf>central</mirrorOf>
        </mirror>
    </mirrors>
    <profiles>
        <profile>
            <id>jdk-1.8</id>
            <activation>
                <activeByDefault>true</activeByDefault>
                <jdk>1.8</jdk>
            </activation>
            <properties>
                <maven.compiler.source>1.8</maven.compiler.source>
                <maven.compiler.target>1.8</maven.compiler.target>
                <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
            </properties>
        </profile>
    </profiles>
</settings>
<!-- 路径：版本其实是3.8.3
/usr/local/apache-maven-3.8.1/conf -->
```
## nacos需要的url地址
```bash
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://localhost:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&serverTimezone=UTC
db.user=root
db.password=youdontkonw
```