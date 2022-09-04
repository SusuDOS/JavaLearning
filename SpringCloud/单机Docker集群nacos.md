# 单机Docker部署nacos集群

部署方式，使用docker-compose部署docker服务.

<font color="red">集群配置时候，-v挂载模式出现容器文件覆盖了宿主文件，最后使得根本配置不上！请到单机docker nacos配置方式查看具体配置情况</font>


## 安装DockerCompose

```bash
wget https://github.com/docker/compose/releases/download/v2.9.0/docker-compose-linux-x86_64 -O /usr/local/bin/docker-compose

chmod a+x /usr/local/bin/docker-compose
```
## 整个文件夹结构

```bash
DockerDistribution/
|-- docker-compose.yml
|-- gateway
|   |-- app.jar
|   `-- Dockerfile
|-- mysql
|   `-- Dockerfile
|-- order-service
|   |-- app.jar
|   `-- Dockerfile
`-- user-service
    |-- app.jar
    `-- Dockerfile
```

## 编写Dockerfile
```docker
FROM java:8-alpine
COPY ./app.jar /tmp/app.jar
ENTRYPOINT java -jar /tmp/app.jar
```
## 编写compose文件

```docker
version: "3.9"

services:
  nacos:
    image:  nacos/nacos-server:v2.1.0
    environment:
      MODE: standalone
    ports:
      - "8848:8848"
  mysql:
    image: mysql:5.7.25
    environment:
      MYSQL_ROOT_PASSWORD: root
    volumes:
      - "/usr/local/etc/mysqlContain/log/:/var/log/"
      - "/usr/local/etc/mysqlContain/data/:/var/lib/mysql/"
      - "/usr/local/etc/mysqlContain/conf/hmy.cnf:/etc/mysql/conf.d/hmy.cnf"
    ports:
      - "3306:3306"
  userservice:
    build: ./user-service
  orderservice:
    build: ./order-service
  gateway:
    build: ./gateway
    ports:
      - "10010:10010"
```
## 运行部署

```
# 切换到compose目录
cd DistributionDocker

# 运行
docker-compose up -d

# 重启部分服务
docker-compose restart userservice orderservice gateway

# 关闭所有容器并且删除容器
docker-compose down

# 测试服务正常，使用如下连接：
http://localhost:10010/user/1?authorization=admin
http://localhost:10010/order/101?authorization=admin
http://localhost:10010/nacos
# 删除当前文件下app.jar，递归查找.
find . -name "app.jar" | xargs rm -rf
```

# 删除所有容器

```bash
# 停止所有容器
docker stop $(docker ps -aq)

# 移除所有容器
docker rm $(docker ps -aq)
```
## 宿主-容器V文件覆盖问题

- 一般情况下，宿主直接覆盖容器中的配置文件。
- 容器覆盖宿主需要如下操作，个人认为这就是Nacos容器覆盖宿主的原因。

```bash
version: '3.9'

volumes:
  web-html:
    name: web-html
    driver: local
    driver_opts:
      o: bind
      type: none
      device: /volumes/web/html

services:
  nginx:
    image: yxs970707/deploy-web-demo:1.0.0
    container_name: web
    restart: always
    ports:
      - 7777:80
    volumes:
      - web-html:/usr/share/nginx/html
```

# 真正的单机nacos集群

单台电脑运行docker，形成一个容器运行讴歌nacos，形成集群，具体配置信息如下所示，经测试，完美可运行：

- docker-compose.yml文件配置

```yml
version: "3.9"

services:
  mysql:
    image: mysql:5.7.25
    environment:
      MYSQL_ROOT_PASSWORD: root
    volumes:
      - "/usr/local/etc/mysqlContain/log/:/var/log/"
      - "/usr/local/etc/mysqlContain/data/:/var/lib/mysql/"
      - "/usr/local/etc/mysqlContain/conf/hmy.cnf:/etc/mysql/conf.d/hmy.cnf"
    ports:
      - "3306:3306"
  nacos1:
    image:  nacos/nacos-server:v2.1.0
    environment:
      MODE: cluster
      SPRING_DATASOURCE_PLATFORM: mysql
      MYSQL_SERVICE_HOST: mysql
      MYSQL_SERVICE_PORT: 3306
      MYSQL_SERVICE_USER: root
      MYSQL_SERVICE_PASSWORD: root
      MYSQL_SERVICE_DB_NAME: nacos_config
    ports:
      - "8841:8848"
  nacos2:
    image:  nacos/nacos-server:v2.1.0
    environment:
      MODE: cluster
      SPRING_DATASOURCE_PLATFORM: mysql
      MYSQL_SERVICE_HOST: mysql
      MYSQL_SERVICE_PORT: 3306
      MYSQL_SERVICE_USER: root
      MYSQL_SERVICE_PASSWORD: root
      MYSQL_SERVICE_DB_NAME: nacos_config
    ports:
      - "8842:8848"
  nacos3:
    image:  nacos/nacos-server:v2.1.0
    environment:
      MODE: cluster
      SPRING_DATASOURCE_PLATFORM: mysql
      MYSQL_SERVICE_HOST: mysql
      MYSQL_SERVICE_PORT: 3306
      MYSQL_SERVICE_USER: root
      MYSQL_SERVICE_PASSWORD: root
      MYSQL_SERVICE_DB_NAME: nacos_config
    ports:
      - "8843:8848"
  nginx:
    image: nginx:stable
    volumes:
      - "/usr/local/etc/nginxContain/conf/nginx.conf:/etc/nginx/nginx.conf"
    ports:
      - "8080:80"

```
- nginx文件配置

```conf
user  nginx;
worker_processes  auto;

error_log  /var/log/nginx/error.log notice;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    keepalive_timeout  65;

    include /etc/nginx/conf.d/*.conf;
    upstream cluster-nacos {
        server nacos1:8848;
        server nacos2:8848;
        server nacos3:8848;
    }
    server {
        listen       80;
        server_name  smilecat.gaoshuye.top;

        location /nacos {
            proxy_pass http://cluster-nacos;
        }
    }
}
```

- hmy.conf配置文件

```conf
[mysqld]
skip-name-resolve
character_set_server=utf8
datadir=/var/lib/mysql
server-id=1000
```