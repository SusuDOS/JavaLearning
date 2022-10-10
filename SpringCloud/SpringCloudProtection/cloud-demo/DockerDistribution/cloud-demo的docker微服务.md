# cloud-demo的docker微服务部署

- 第一步：cloud-demo目录下运行：mvn clean && mvn package
- 第二步：将得到的app.jar文件移动到DockerDistribution下对应的目录中
- 第三步：运行：docker-compose up -d
- 第四步：可能需要重启一些服务，运行：`docker-compose restart gateway userservice orderservice`
- 测试部署OK.

```bash
http://smilecat.gaoshuye.top:10010/user/1?authorization=admin
http://smilecat.gaoshuye.top:10010/order/103?authorization=admin
```
## docker-compose配置
测试前期可以将每一个微服务暴露端口，后期不再暴露，通过 `docker-compose` 文件部署的服务可以使用容器名称调用，默认容器名称为：`文件夹_服务名`，可以自定义指定容器名称，方便容器之间互相调用。

文件名:`docker-compose.yml`

```bash
version: "3.9"
services:
  nacos:
    container_name: nacos
    image: nacos/nacos-server:v2.1.0
    environment:
      MODE: standalone
    ports:
      - "8848:8848"
  mysql:
    container_name: mysql
    image: mysql:5.7
    environment:
      MYSQL_ROOT_password: abc123456
    volumes:
      - "/usr/local/etc/mysqlContain/log/:/var/log/"
      - "/usr/local/etc/mysqlContain/data/:/var/lib/mysql/"
      - "/usr/local/etc/mysqlContain/conf/hmy.cnf:/etc/mysql/conf.d/hmy.cnf"
    ports:
      - "3306:3306"
  userservice:
    container_name: userservice
    build: ./user-service
  orderservice:
    container_name: orderservice
    build: ./order-service
  gateway:
    container_name: gateway
    build: ./gateway
    ports:
      - "10010:10010"
```
- hmy 文件名称
```bash
[mysqld]
skip-name-resolve
character_set_server=utf8
datadir=/var/lib/mysql
server-id=1000
```