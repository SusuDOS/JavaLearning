# 会话共享信息

## 浏览器:Cookies

第一次请求时，服务器生成Cookie并添加在Response中，浏览器发送任何请求默认带浏览器的所有Cookies。


## 服务端:Session

- 浏览器第一次请求时,为浏览器插件一个session会话空间,判断是否是第一次的依据是浏览器传过来的cookie，服务器session默认存储30分钟.

- 配置有效期方式:

```xml
<!-- 项目中配置 -->
<!-- webapp/WEB-INF/web.xml -->
<!-- 设置有效期100分钟 -->
<session-config>
    <session-timeout>100</session-timeout>
</session-config>

<!-- tomcat服务器的文件为:web.xml  -->
<session-config>
    <session-timeout>100</session-timeout>
</session-config>
```

## 登陆界面

- brand-demo

```
使用servlet技术做的登陆界面，个人认为与典型框架的springboot不太贴合有机会再好好研读。
```