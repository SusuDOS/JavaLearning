# 微服务保护

采用阿里巴巴的Sentinal框架，该技术已经被spring整合。需要在cloud-demo下<font color=red>mvn clean compile</font>否则user-service可能无法运行！

- 资料文档在`source`目录下

- 代码在`cloud-demo`目录下

## 启动nacos和msql

- 启动必备docker服务
```bash
docker-compose up -d
```

- 导入sql数据
```sql
mysql -h 127.0.0.1 -u root -pabc123456
source cloud-order.sql;
source cloud-user.sql;

# user
http://localhost:8081/user/1

# order
http://localhost:8088/order/101
```

## 启动sentinel

```bash
java -jar sentinel-dashboard-1.8.5.jar -Dserver.port=8090
```

## 限流

- 直接限流

- 关联限流

- 链路限流

## 熔断&降级策略

### 仓壁模式

- 限制线程数量

### 熔断策略

- 慢调用

- 异常比例

- 异常数

## 热点

- 资源细度更佳

- 需要给一个注解

```java
// hot可以被任意替换.
@SentinelResource("hot")
```

## 授权

- 只要是从网关过来的数据都添加一个头,通过判断头确定是否放行.

```yaml
值为gateway,可以自定义.
- AddRequestHeader=origin,gateway
```

- 无需任何代码，也可以添加AuthorizeFileter里面的内容.

```url
http://localhost:10010/user/1?authorization=admin
http://localhost:10010/order/103?authorization=admin
```

## nacos持久化guize

- 改动巨大，请按照文档 `sentinel规则持久化.md` 操作。