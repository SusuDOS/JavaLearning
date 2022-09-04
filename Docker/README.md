## 资料位置

SpringCloud+RabbitMQ+Docker+Redis+搜索+分布式\实用篇\学习资料\day03-Docker\资料

- 核心演示微服务部署 `cloud-demo`
- docker build文件
- docker部署cloud-demo,nacos集群，文件在SpringCloud下！
- 搭建带ui界面的docke镜像仓库私库

## 宿主与容器覆盖规则

这点是极其重要的知识点！

```bash
若容器目录为普通目录，则宿主一定覆盖容器目录！

若容器目录为工作目录，宿主空，则：容器覆盖宿主；宿主非空，则：宿主覆盖容器！

疑惑点一：nginx容器内/usr/share/nginx/html/按理说是工作目录，宿主空，则容器覆盖宿主，实际上确实宿主都会覆盖容器！(除非不是工作目录。)
```
## 删除容器&镜像

```bash
# 停止所有容器
docker stop $(docker ps -aq)

# 删除所有容器
docker rm $(docker ps -aq)

# 删除所有镜像
docker rmi $(docker images -q)
```

## docker-compose常用

```bash
docker-compose up -d
docker-compose down
docker-compose restart userservice orderservice gateway
docker-compose logs -f
```

## docker部署一些服务演示

- # MySQL

```bash
docker run -p 3306:3306 --name mysql --rm   \
-v /usr/local/etc/mysqlContain/log/:/var/log/  \
-v /usr/local/etc/mysqlContain/data/:/var/lib/mysql/  \
-v /usr/local/etc/mysqlContain/conf/hmy.cnf:/etc/mysql/conf.d/hmy.cnf \
-e MYSQL_ROOT_PASSWORD=root -d mysql:5.7.25
```
- 注意登陆必须带ip：`mysql -h 127.0.0.1 -uroot -p`
- # Nginx服务器

```bash
docker run --rm -d --name nginx -p 48099:80 -v /usr/local/etc/nginxContain/data:/usr/share/nginx/html nginx:stable
```
- 访问演示：http://gy.bzcv.cn:8099/MainPianProfile.zip

- # RabbitMQ

```bash
docker run -d  --hostname my-rabbitTest --name rabbitmq \
-e RABBITMQ_DEFAULT_USER=itcast \
-e RABBITMQ_DEFAULT_PASS=123321 \
-p 15673:15672 \
-p 5673:5672 --rm rabbitmq:3-management
```
- Rabbit API 500错误
```bash
# 构建路径，创建文件.
mkdir -p /tmp/rabbitmq/conf.d/management_agent.disable_metrics_collector.conf
vim /tmp/rabbitmq/conf.d/management_agent.disable_metrics_collector.conf

# cat /etc/rabbitmq/conf.d/management_agent.disable_metrics_collector.conf 
management_agent.disable_metrics_collector = true
# 进入rabbitmq
docker start rabbitmq
docker exec -it rabbitmq bash

# 启用插件.
rabbitmq-plugins enable rabbitmq_management

# Rabbit API 500错误.
cd /etc/rabbitmq/conf.d/
echo management_agent.disable_metrics_collector = false > management_agent.disable_metrics_collector.conf
management_agent.disable_metrics_collector = true

config get repl_backlog_buf 
```

- # Redis
```bash
# redis start
docker run --name redis --rm -p 6379:6379 -d redis
```

- # Nacos单机模式

```bash
docker run --rm -d --name nacos -e MODE=standalone -p 8848:8848 -p 9848:9848 nacos/nacos-server:v2.1.0
```

- 配置有效性测试

```bash
# 有效性测试
userservice-dev.yaml
pattern:
  dateformat: yyyy年MM月dd日 HH:mm:ss
```