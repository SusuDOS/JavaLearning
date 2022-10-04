# JavaWeb

2021JavaWeb(IDEA)高效版。

<font color=red>本文件夹记录资料不够完整...</font>

## MySql

## JDBC&Driud

## Mybatis

- XML与注解方式操作数据库.

- mybatisx idea的辅助插件帮助建立Mapper与函数自建的关系.

- XMl写动态SQL.

## 文档查看

- JavaScript

地址: [https://www.w3school.com.cn/jsref/event_onblur.asp](https://www.w3school.com.cn/jsref/event_onblur.asp)

## 请求响应携带数据

<font color=red> 极其重要,请求与响应的信息携带。请求行,请求头，请求体.</font>

- tomcat服务器配置启动

- idea中使用tomcat:集成本地和依赖.

- Servlet接口: 具体内容待续.

```java
任何一个类实现该接口重写五大方法,在类上添加.
@WebServelet("/demo")
class demo implements Servlet{
    // 五大方式}

FileInputStream fis = new FileInputStream("d://a.jpg");
ServletOutputStream os = response.getOutputStream();

// 3. 完成流的copy
/*
* byte[] buff = new byte[1024];
* int len = 0;
* while ((len = fis.read(buff))!= -1){
* os.write(buff,0,len);
* }
*/

IOUtils.copy(fis, os);
fis.close();
```



## 会话共享信息

- 启动服务

```bash
# 命令行启动服务
mvn tomcat7:run

# ctrl+c关闭服务，服务器会保存session到服务器.
# 保存文件名称
SESSIONS.ser
```

- session和cookie：参看cookie-demo

已经理解了session和cookie的本质，发送请求的在web的常用参数.




