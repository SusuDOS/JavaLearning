# nacos集群

## 运行配置

- 运行容器

```bash
docker-compose up -d
```

- nacos数据库

```bash
mysql -h 127.0.0.1 -uroot -pabc123456
source nacos.sql
```

- nginx代理配置文件

```config
user  nginx;
worker_processes  auto;

events {
    worker_connections  1024;
}

http {
    include       mime.types;
    default_type  application/octet-stream;

    sendfile        on;
    keepalive_timeout  65;

    upstream nginx-cluster{
        # 没有work,用ip.
        # server nacos1:8841;
        # server nacos2:8842;
        # server nacos3:8843;
        server 91.199.209.120:8841;
        server 91.199.209.120:8842;
        server 91.199.209.120:8843;        
    }
    server {
        listen       80;
        listen  [::]:80;
        server_name   smilecat.gaoshuye.top;

        location /nacos {
            proxy_pass http://nginx-cluster;
        }

        location / {
            root   /usr/share/nginx/html;
            index  index.html index.htm;
        }

        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   /usr/share/nginx/html;
        }
    }
}
```

- mysql配置文件

```config
[mysqld]
skip-name-resolve
character_set_server=utf8
datadir=/var/lib/mysql
server-id=1000
```

## 运行逻辑

- user-service根据用户id查询用户信息.

- order-service根据订单id查询订单，再根据用户id调用user-service查询用户信息，并且封装为一个整体json.

- 