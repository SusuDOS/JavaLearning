> **安装环境:Win10 64位**
> **软件版本:MySQL 5.7.24 解压版**

## 一、下载

点开下面的链接：
[https://downloads.mysql.com/archives/community/](https://downloads.mysql.com/archives/community/ "https://downloads.mysql.com/archives/community/")


不用理会上面的登录和注册按钮，直接点击`No thanks, just start my download.`就可以下载。


## 二、安装(解压)

下载完成后我们得到的是一个压缩包，将其解压，我们就可以得到MySQL 5.7.24的软件本体了(就是一个文件夹)，我们可以把它放在你想安装的位置。


## 三、配置

### 1. 添加环境变量

> 环境变量里面有很多选项，这里我们只用到`Path`这个参数。为什么在初始化的开始要添加环境变量呢？
> 在黑框(即CMD)中输入一个可执行程序的名字，Windows会先在环境变量中的`Path`所指的路径中寻找一遍，如果找到了就直接执行，没找到就在当前工作目录找，如果还没找到，就报错。我们添加环境变量的目的就是能够在任意一个黑框直接调用MySQL中的相关程序而不用总是修改工作目录，大大简化了操作。


右键`此电脑`→`属性`，点击`高级系统设置`


在`系统变量`中新建`MYSQL_HOME`


在`系统变量`中找到并**双击**`Path`
```
%MYSQL_HOME%\bin
```

最后点击确定。

**如何验证是否添加成功？**

右键开始菜单(就是屏幕左下角)，选择`命令提示符(管理员)`，打开黑框，敲入`mysql`，回车。
如果提示`Can't connect to MySQL server on 'localhost'`则证明添加成功；
如果提示`mysql不是内部或外部命令，也不是可运行的程序或批处理文件`则表示添加添加失败，请重新检查步骤并重试。

### 2. 新建配置文件

新建一个文本文件，内容如下：

```
[mysql]
default-character-set=utf8

[mysqld]
character-set-server=utf8
default-storage-engine=INNODB
sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION
```
把上面的文本文件另存为，在保存类型里选`所有文件 (*.*)`，文件名叫`my.ini`，存放的路径为MySQL的`根目录`(例如我的是`D:\software\mysql-5.7.24-winx64`,根据自己的MySQL目录位置修改,个人喜欢使用`mysql.ini`作为文件名称,但是实际使用各种出现错误，严格按照教程即可)。

上面代码意思就是配置数据库的默认编码集为utf-8和默认存储引擎为INNODB。

### 3. 初始化MySQL

以管理员方式运行CMD，键入`mysqld --initialize-insecure`，回车，稍微等待一会，如果出现没有出现报错信息则证明data目录初始化没有问题，此时再查看MySQL目录下已经有data目录生成。

```
mysqld --initialize-insecure
```


### 4. 注册MySQL服务

在黑框里敲入`mysqld -install`，回车。

```
mysqld -install
```

现在你的计算机上已经安装好了MySQL服务了。

### 5. 启动MySQL服务

在CMD中键入`net start mysql`，回车。

```java
net start mysql  // 启动mysql服务
    
net stop mysql  // 停止mysql服务
```

### 6. 修改默认账户密码

在黑框里敲入`mysqladmin -u root password 123456`，这里的`123456`就是指默认管理员(即root账户)的密码，可以自行修改成你喜欢的。

```
mysqladmin -u root password 123456

# 第二次修改需要加上旧密码.
mysqladmin -uroot -p123456 password root
```


**至此，MySQL 5.7 解压版安装完毕！**

------

## 四、登录MySQL

右键开始菜单，选择`命令提示符`，打开黑框。
在黑框中输入，`mysql -uroot -p1234`，回车，出现下图且左下角为`mysql>`，则登录成功。

```
mysql -uroot -p1234
```

**到这里你就可以开始你的MySQL之旅了！**

退出mysql则在SQL登陆中键入如下：

```
exit
quit
```

登陆参数，注意没有空格对参数选项和参数间隔开：

```
mysql -u用户名 -p密码 -h要连接的mysql服务器的ip地址(默认127.0.0.1) -P端口号(默认3306)
```

------

## 五、卸载MySQL

如果你想卸载MySQL，也很简单。

右键开始菜单，选择`命令提示符(管理员)`，打开黑框。

1. 先停止mysql服务，敲入`net stop mysql`，回车。

```
net stop mysql
```

2. 再敲入`mysqld -remove mysql`，回车即可移除服务。

```
mysqld -remove mysql
```

3. 最后删除MySQL解压的目录及相关的环境变量即可完成卸载。

**至此，MySQL卸载完成！**

## 六 、Linux的MySQL的操作


- 查找Linux占用端口服务&并干掉
```bash
lsof -i:端口号
kill -9 num_pid
```

### Linux安装MySQL

```bash
apt-get install mysql-server

# 可以选择安装如下：
apt-get install mysql-client

# 默认是开启root用户的空密码登陆的.

# 查看
cat /etc/mysql/debian.cnf

# 使用生成的密码登陆
# Automatically generated for Debian scripts. DO NOT TOUCH!
[client]
host     = localhost
user     = debian-sys-maint
password = iqhZ4BsjJvWsGXfy
socket   = /var/run/mysqld/mysqld.sock
[mysql_upgrade]
host     = localhost
user     = debian-sys-maint
password = iqhZ4BsjJvWsGXfy
socket   = /var/run/mysqld/mysqld.sock
```
### MySQL管理用户权限
```bash
# 创建dev_gaoming，密码dev_gaoming_3389
CREATE USER  dev_gaoming@localhost IDENTIFIED BY 'dev_gaoming_3389';

# 赋予对所有库所有表的所有权限.
grant all privileges on *.* to dev_gaoming@localhost;

# 刷新
flush privileges;

# 非root用户测试连接.

mysql -udev_gaoming -pdev_gaoming_3389
```

### Linux运行JAR包

```bash
# 后台Linux运行JAR包
nohup /usr/bin/java -jar  /root/springboot_09_ssm-0.0.1-SNAPSHOT.jar >/root/nohup.out 2>&1 &
```
### 更多MySQL权限操作

参考连接：
[https://www.cnblogs.com/chenmh/p/4533902.html](https://www.cnblogs.com/chenmh/p/4533902.html "https://www.cnblogs.com/chenmh/p/4533902.html")



## Cent7&MySQL

```bash
# 清理
rpm -qa|grep mysql

rpm -e --nodeps mysql-libs-5.1.*

find / -name mysql
删除查询出来的所有东西

# 安装
wget https://dev.mysql.com/get/mysql80-community-release-el7-3.noarch.rpm
yum localinstall mysql80-community-release-el7-3.noarch.rpm

# 密匙
rpm --import https://repo.mysql.com/RPM-GPG-KEY-mysql-2022
yum update -y

yum install mysql-community-server -y

# 启动
service mysqld start

# 修改密码
## 查看临时密码
grep 'temporary password' /var/log/mysqld.log
mysql -uroot -p

## 修改密码
ALTER USER 'root'@'localhost' IDENTIFIED BY '__rootA338900';

# 查看策略
SHOW VARIABLES LIKE 'validate_password%';

## 修改等级，否则无法设置低安全等级密码-8.0版本无效
set global validate_password.policy=0;
set global validate_password.length=2;


ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';

## 刷新
flush privileges;
```

## 一些细节


Mysql中如果表和表之间建立的外键约束，则无法删除表及修改表结构!

```mysql
# 关闭外键约束方便对表结构操作.
SET FOREIGN_KEY_CHECKS = 0;

# 开启外键约束，防止误操作.
SET FOREIGN_KEY_CHECKS = 1;

然后重新导入即可。

```



