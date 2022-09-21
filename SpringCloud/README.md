# SpringCloud

<font color=red>更多有价值的配置信息查看pinda-authority项目有较多有价值的配置！</font>

## maven配置

maven配置参看本仓库maven文件下的配置信息.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.2.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.2.0 https://maven.apache.org/xsd/settings-1.2.0.xsd">
    <localRepository>/usr/local/apache-maven-3.8.6/MAVEN_RESP</localRepository>
    <mirrors>
        <mirror>
            <id>maven</id>
            <name>office maven</name>
            <url>https://repo.maven.apache.org/maven2/</url>
            <!-- <mirrorOf>central</mirrorOf> -->
            <mirrorOf>*</mirrorOf>
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
```
## nacos需要的url地址

- nacos配置文件核心几行信息

```properties
# nacos/conf/application.properties
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://mysql:3306/nacos_config?characterEncoding=utf8&connectTimeout=3000&socketTimeout=5000&autoReconnect=true&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai
db.user.0=root
db.password.0=abc123456
```
