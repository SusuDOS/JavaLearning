# Maven高级部分

## Maven依赖

- 依赖传递

针对依赖传递问题，从POM依赖分析的效果上来说都可以不再依赖中显示出来，不过似乎仍然有一些不小的区别。

比如:我做了一个B项目，需要打包成为A项目的依赖但是我不希望B的依赖被A使用，则可以在B的POM中主动断开依赖关系。

而若我没有没有进行任何配置，此时A不想用只能使用排除依赖排除B中的依赖。

```bash
# 可选依赖
<optional>true</optional>

# 排除依赖
<exclusions>
    <exclusion>  <!-- declare the exclusion here -->
        <groupId>sample.ProjectC</groupId>
        <artifactId>Project-C</artifactId>
    </exclusion>
</exclusions> 
```

- 依赖聚合

```bash
简单点理解就是对整个项目进行整体打包，防止由于局部修改导致整个项目不可用。比如最底层A的一个对象有五个属性，现在删除了一个属性，此时依赖与A的不再可用，需要重新打包！
```

- 依赖管理

```bash
配置可能使用的依赖和版本，能一改全改整个项目依赖，相对高效不出错！
```

- POM变量读取

```xml
<!-- 变量设置 -->
<properties>
    <spring.version>5.2.10.RELEASE</spring.version>
    <junit.version>4.12</junit.version>
    <mybatis-spring.version>1.3.0</mybatis-spring.version>
    <!--<jdbc.url>jdbc:mysql://127.0.0.1:3306/ssm_db</jdbc.url>-->
</properties>

<!-- 变量使用 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>${spring.version}</version>
</dependency>
```
- POM变量读取到properties

具体参看 `maven_01_parent` 的pom文件。

```xml
<build>
    <resources>
        <!-- 使得properties可以将属性值读取到 propertier 中. -->
        <!--设置资源目录，并设置能够解析${}-->
        <!-- 设置可以让其他模块在对应的模块resources中使用该pom中定义properties属性-->
        <resource>
            <directory>${project.basedir}/src/main/resources</directory>
            <filtering>true</filtering>
        </resource>
    </resources>
</build>
```

## 打 `war` 包报错问题

```xml
<build>
    <plugins>
      <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.1</version>
        <configuration>
          <port>80</port>
          <path>/</path>
        </configuration>
      </plugin>
      <!-- 解决问题的关键部分 -->
      <!-- 解决打成war包，模块中的resource中没有web.xml会报错,也可以直接在模块的resource中写web.xml-->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.2.3</version>
        <configuration>
          <failOnMissingWebXml>false</failOnMissingWebXml>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```

## 多环境配置
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
# 具体参看maven_01_parent的pom文件对测试部分进行精准的细粒度控制.
```

## Maven自建私服

- Nexus私服的搭建问题...

## 资料位置

- 源自 `2022版SSM框架` 视频配套资源

- 所有资料在文件 `基础框架笔记` 中 ...