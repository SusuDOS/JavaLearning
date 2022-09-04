# RabbitMQ高级

## 启动RabbitMQ

```bash
# 启动RabbitMQ的docker容器.
bash dockerStart.sh

# 进入容器启用插件.
docker exec -it rabbit bash
rabbitmq-plugins enable rabbitmq_delayed_message_exchange

# 若看到以下则开启成功.
"""Applying plugin configuration to rabbit@rabbit...
The following plugins have been enabled:
  rabbitmq_delayed_message_exchange

started 1 plugins."""

# 关闭容器
docker stop rabbit
```

## 仲裁队列

在rabbitmq第3.8版本开始，仲裁队列替代镜像队列，默认为队列在整个集群中构建4个备份，外加一个主，合计5个队列，同时在yml中配置时候不再使用host+port描述rabbitmq连接而是address连接方式。

- 创建网络

```bash
docker network create mq-net
```

- 处理配置

```bash
# rabbitmq.conf
loopback_users.guest = false
listeners.tcp.default = 5672
cluster_formation.peer_discovery_backend = rabbit_peer_discovery_classic_config
cluster_formation.classic_config.nodes.1 = rabbit@host1
cluster_formation.classic_config.nodes.2 = rabbit@host2
cluster_formation.classic_config.nodes.3 = rabbit@host3

# cookie,类似于验证密码的功能.
# .erlang.cookie,也可以修改权限为只读 chmod 600 .erlang.cookie
# 写入cookie
echo "FXZMCVGLBIXZCDEMMVZQ" > .erlang.cookie
# 修改cookie文件的权限
```

- 启动集群

```bash
bash dockerQuorum.sh 
```

















