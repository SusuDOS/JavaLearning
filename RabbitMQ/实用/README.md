# RabbitMQ指南

## 单机版运行指南

-  # 拉取
```bash
docker pull rabbitmq:3-management
```
- # 载入模式

```bash
docker load -i mq.tar
```

- # 运行

```bash
docker run --rm \
 -e RABBITMQ_DEFAULT_USER=itcast \
 -e RABBITMQ_DEFAULT_PASS=123321 \
 --name rabbitmq \
 --hostname mq1 \
 -p 15672:15672 \
 -p 5672:5672 \
 -d rabbitmq:3-management
```








