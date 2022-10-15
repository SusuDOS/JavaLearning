# RabbitMQ高级

## 发送者消息确认机制

- 发送者需要确认正确投递到交换机,和队列.

## 消费者消息确认机制

- 1. 消费者必须正确消费消息才算完成

- 2.1 消费者直接打回给rabbit，会导致无限重试。

- 2.2 消费者本地重试确实无法消费默认会拒绝导致消息丢失。

- 2.3 设置本次尝试失败后由消费者将消息投递到指定交换机(error.direct)->队列(error.queue)

```bash
none：rabbit会在消费者取了消息之后就删除消息；
auto：若消费者不设置重试则消费端直接将消息打回rabbit，若设置尝试次数，耗尽次数，消费端和rabbit中都不会存在消息.

本java文件就是实现:
指定消息处理:由消费端将无法处理的异常消息投递到交换机("error.direct")->队列("error.queue");
```

- 死信交换机

## 启动RabbitMQ

```bash
# 启动RabbitMQ的docker容器.
bash dockerStart.sh

# 进入容器启用插件:死信交换机
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

<font color=red size=4>特别提醒该文件必须创建，复制过来的文件是无法使用。貌似三个配置完全一致直接使用rabbitmq1的即可正常使用.</font>

- 启动集群

```bash
bash dockerQuorum.sh 
```

















