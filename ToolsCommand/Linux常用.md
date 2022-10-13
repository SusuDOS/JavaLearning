# 远程编程配置

注意不同的系统，可能有差异...

## 标准用户创建

vim /etc/hostname

```bash
# 新增
groupadd dev

# 删除
groupdel dev

# 创建用户到dev组，带home目录
useradd -m -g dev gaoming

## 分配sudo权限
# ubuntu
usermod -G sudo gaoming

# centos
usermod -G wheel gaoming

# 修改用户Gaoming的登录bash，高亮显示.
usermod -s /bin/bash gaoming

# 设置用户密码
passwd gaoming

# 切换到gaoming用户:su - gaoming
su gaoming 

# 长时间需要权限运行，不用带sudo即可运行.
sudo su

# 修改文件|目录的拥有者，文件夹带选项-R.
chown 用户名 文件名|目录名

# 递归修改文件|目录的组
chgrp -R 组名 文件名|目录名

# 递归 查找当前路径中 以.out结尾的文件，并且删除！
find . -name "*.out*" | xargs rm -f
find . -name "*.out*" | xargs rm

# 移动文件.
find . -size +50M -exec mv {} ../ \;

# 分别打包 文件夹中的 文件和文件夹.
ls | awk '{ print "zip -r "$0".zip " $0|"/bin/bash" }'

# 查看文件夹中 各个文件，文件夹大小！
du -h –max-depth=1 *

# yt-dlp下载文件格式
nohup yt-dlp --restrict-filenames -P MaiPianoProfile -o "%(title)s.mp4" --concurrent-fragments 8 https://cn.video.com/model/liu &
```

## 免密SSH登陆
```bash
ssh-keygen -t rsa

# 保存当前主机公钥到目标主机：当前免密访问目标主机！也可以使用cat命令行写入...
mkdir -p ~/.ssh/ && vim ~/.ssh/authorized_keys
```

## Conda环境
```bash
wget https://repo.anaconda.com/miniconda/Miniconda3-latest-Linux-x86_64.sh
bash Miniconda3-latest-Linux-x86_64.sh
conda create -n test python=3.7
conda activate test
```

## 特殊删除
```bash
# 查看当前路径下的文件夹
ls -l . |awk '/^d/ {print $NF}'


# 删除当前路径下的文件夹，不删除文件.
dir=$(ls -l . |awk '/^d/ {print $NF}')
for i in $dir
do
 rm -rf $i
done

# 删除当前路径小于60M的文件,print0解决空格文件名问题，此时：换行为null而不是\n.
find ./ -type f -size -60M -print0 | xargs -L1 -0 rm
```
## zip打包压缩&分片

```bash
# 删除当前文件夹下以 "*.out" file文件，递归方式.
find . -name "*.out" | xargs rm -f

# ls当前目录，每个文件0r文件夹打包一次！
ls | awk '{ print "tar zcvf "$0".tgz " $0|"/bin/bash" }'

# or use zip ...
# 带密码,zip 打包
zip -9r -P'123456' moonforce.zip moonforce
# unzip file what has passwd.
unzip -P'123456' mimvp.zip -d mimvp

# 无密码打包文件夹，且递归子文件夹打包
zip -r moonforce.zip moonforce
# unzip file what has passwd.
unzip mimvp.zip -d mimvp

# 边打包边分片.
zip -s 8000m -r test.zip test/

# zip打包后分片
zip -r moonforce.zip moonforce/
zip moonforce.zip --out moonforce_split.zip -s 8000m

# 合并分片，再解压.
zip -s 0 moonforce01.zip --out moonforce01_big_full.zip
unzip moonforce01_big_full.zip

# 将.mp4结尾的文件移动到bak文件夹下
find . -name "*.mp4"  -exec mv {} ./bak/ \;
```



## ipv6启用&禁用

### 可以参考如下：[https://linux.cn/article-12689-1.html](https://linux.cn/article-12689-1.html "也可以参看具体配置！")

```bash
# 查看ipv6，开启时候有inet6信息，禁用则没有！
ip a

# 禁用ipv6
# 临时禁用
sudo sysctl -w net.ipv6.conf.all.disable_ipv6=1
sudo sysctl -w net.ipv6.conf.default.disable_ipv6=1
sudo sysctl -w net.ipv6.conf.lo.disable_ipv6=1

# 永久禁用
vi /etc/sysctl.conf

net.ipv6.conf.all.disable_ipv6=1
net.ipv6.conf.default.disable_ipv6=1
net.ipv6.conf.lo.disable_ipv6=1

# 修改生效
sysctl -p

# 启用ipv6
# 临时启用
sudo sysctl -w net.ipv6.conf.all.disable_ipv6=0
sudo sysctl -w net.ipv6.conf.default.disable_ipv6=0
sudo sysctl -w net.ipv6.conf.lo.disable_ipv6=0

# 永久启用
vi /etc/sysctl.conf

net.ipv6.conf.all.disable_ipv6=0
net.ipv6.conf.default.disable_ipv6=0
net.ipv6.conf.lo.disable_ipv6=0

# 生效
sysctl -p
```

正常情况下有效，无需重启！！！！备注：若上面方式仍然在重启后ipv6生效，没有被禁用掉，则：

```bash
vim /etc/rc.local

# 追加内容，注意有:exit 0
/etc/sysctl.d
/etc/init.d/procps restart
exit 0

# 修改权限
chmod 755 /etc/rc.local

# 重启电脑
reboot
```
### 关于GRUB方式

使用上面的方式可以正常实现，可以使用第二种这种方式实现，建议上面的那种！

```bash
# vim /etc/default/grub
>>>>>>change >>>>>>>>
GRUB_CMDLINE_LINUX_DEFAULT="splash netcfg/do_not_use_netplan=true net.ifnames=0 biosdevname=0"
GRUB_CMDLINE_LINUX=""

>>>>>>changeed>>>>>>>>
GRUB_CMDLINE_LINUX_DEFAULT="quiet splash ipv6.disable=1"
GRUB_CMDLINE_LINUX="ipv6.disable=1"

# 生效
update-grub

## 若上述命令不存在则：
sudo grub-mkconfig -o /boot/grub/grub.cfg

# finally reboot vps
reboot
```

## Linux下Java环境

```
# 下载解压
wget http://170.178.192.228/jdk-8u333-linux-x64.tar.gz
tar -xzvf jdk-8u333-linux-x64.tar.gz -C /usr/java/

# 配置环境变量,尽量在EXPORT...后面，不在应该也没关系.
vim /etc/profile


export JAVA_HOME=/usr/local/jdk1.8.0_333
export CLASSPATH=$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib
export PATH=$JAVA_HOME/bin:$PATH

# 刷新环境
source /etc/profile
```

## Linux下Maven

```bash
wget https://dlcdn.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz

tar -xzvf apache-maven-3.8.6-bin.tar.gz

vi /etc/profile

# 追加内容.
export MAVEN_HOME=/usr/local/apache-maven-3.8.6
export PATH=$MAVEN_HOME/bin:$PATH

# 重新加载配置文件
source /etc/profile
```

## maven仓库

```bash
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.2.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.2.0 https://maven.apache.org/xsd/settings-1.2.0.xsd">
    <localRepository>/usr/local/apache-maven-3.8.6/MAVEN_RESP</localRepository>
    <mirrors>
        <mirror>
            <id>alimaven</id>
            <name>office maven</name>
            <url>https://repo.maven.apache.org/maven2/</url>
            <mirrorOf>central</mirrorOf>
        </mirror>
    </mirrors>
    <profiles>
        <profile>
            <id>jdk-1.8</id>
            <activation>
                <activeByDefault>true</activeByDefault>
                <jdk>1.8</jdk>
            </activation>
            <properties>
                <maven.compiler.source>1.8</maven.compiler.source>
                <maven.compiler.target>1.8</maven.compiler.target>
                <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
            </properties>
        </profile>
    </profiles>
</settings>
<!-- 路径：版本其实是3.8.3
/usr/local/apache-maven-3.8.1/conf -->
```

## 删除 美式键盘 简体中文 输入法

- 第一种

 ```bash
建议打开设置 -> 时间和语言 -> 语言和地区
在首选语言下添加一下 “英文” 并设置到第一位,然后删除“中文”。

重启电脑，重新在首选语言下添加“中文”，并设置到第一位。
看下是否还有 简体 中文 美式键盘!
 ```

- 第二种

```bash
1.位置
计算机\HKEY_USERS\.DEFAULT\Keyboard Layout\Preload
计算机\HKEY_CURRENT_USER\Keyboard Layout\Preload

2.删除所找到的00000804键值，合计两处！

3.重启电脑！
```

## Ubuntu中文乱码

```bash
apt install language-pack-zh-hans

vim /etc/environment
LANG="zh_CN.UTF-8"
LANGUAGE="zh_CN:zh:en_US:en"

vim /var/lib/locales/supported.d/local
en_US.UTF-8 UTF-8
zh_CN.UTF-8 UTF-8
zh_CN.GBK GBK
zh_CN GB2312

# 生成
locale-gen

# 使其有效，必须执行
vim .bashrc
export LANG="zh_CN.UTF-8"
export LC_ALL="zh_CN.UTF-8"

# Ubuntu20出现：LC_ALL: cannot change locale (zh_CN.UTF-8)
# Ubuntu18不会出现
vim /etc/profile
export LC_ALL=C;unset LANGUAGE

source .bashrc

# 可能在/etc/profile中修改一样有效。
```

## aria2c

- 安装

```bash
apt install aria2 -y
```

- 下载命令行

```bash
# 单个
aria2c https://download.owncloud.org/community/owncloud-9.0.0.tar.bz2
[#986c80 19MiB/21MiB(90%) CN:1 DL:3.0MiB]
03/22 09:49:13 [NOTICE] Download complete: /opt/owncloud-9.0.0.tar.bz2
Download Results:
gid   |stat|avg speed  |path/URI
======+====+===========+=======================================================
986c80|OK  |   3.0MiB/s|/opt/owncloud-9.0.0.tar.bz2
Status Legend:
(OK):download completed.

# 多个
aria2c -Z https://download.owncloud.org/community/owncloud-9.0.0.tar.bz2 ftp://ftp.gnu.org/gnu/wget/wget-1.17.tar.gz
[DL:1.7MiB][#53533c 272KiB/21MiB(1%)][#b52bb1 768KiB/3.6MiB(20%)]
03/22 10:25:54 [NOTICE] Download complete: /opt/wget-1.17.tar.gz
[#53533c 18MiB/21MiB(86%) CN:1 DL:3.2MiB]
03/22 10:25:59 [NOTICE] Download complete: /opt/owncloud-9.0.0.tar.bz2
Download Results:
gid   |stat|avg speed  |path/URI
======+====+===========+=======================================================
b52bb1|OK  |   2.8MiB/s|/opt/wget-1.17.tar.gz
53533c|OK  |   3.4MiB/s|/opt/owncloud-9.0.0.tar.bz2
Status Legend:
(OK):download completed.

# 限速
aria2c --max-download-limit=500k https://download.owncloud.org/community/owncloud-9.0.0.tar.bz2
[#7f9fbf 21MiB/21MiB(99%) CN:1 DL:466KiB]
03/22 09:54:51 [NOTICE] Download complete: /opt/owncloud-9.0.0.tar.bz2
Download Results:
gid   |stat|avg speed  |path/URI
======+====+===========+=======================================================
7f9fbf|OK  |   462KiB/s|/opt/owncloud-9.0.0.tar.bz2
Status Legend:
(OK):download completed.

# 续传
aria2c -c https://download.owncloud.org/community/owncloud-9.0.0.tar.bz2
[#db0b08 8.2MiB/21MiB(38%) CN:1 DL:3.1MiB ETA:4s]^C
03/22 10:09:26 [NOTICE] Shutdown sequence commencing... Press Ctrl-C again for emergency shutdown.
03/22 10:09:26 [NOTICE] Download GID#db0b08bf55d5908d not complete: /opt/owncloud-9.0.0.tar.bz2
Download Results:
gid   |stat|avg speed  |path/URI
======+====+===========+=======================================================
db0b08|INPR|   3.3MiB/s|/opt/owncloud-9.0.0.tar.bz2
Status Legend:
(INPR):download in-progress.
如果重新启动传输，aria2 将会恢复下载。
# aria2c -c https://download.owncloud.org/community/owncloud-9.0.0.tar.bz2
[#873d08 21MiB/21MiB(98%) CN:1 DL:2.7MiB]
03/22 10:09:57 [NOTICE] Download complete: /opt/owncloud-9.0.0.tar.bz2
Download Results:
gid   |stat|avg speed  |path/URI
======+====+===========+=======================================================
873d08|OK  |   1.9MiB/s|/opt/owncloud-9.0.0.tar.bz2
Status Legend:
(OK):download completed.

# 下载后重命名
aria2c -o owncloud.zip https://download.owncloud.org/community/owncloud-9.0.0.tar.bz2
[#d31304 16MiB/21MiB(74%) CN:1 DL:6.2MiB]
03/22 09:51:02 [NOTICE] Download complete: /opt/owncloud.zip
Download Results:
gid   |stat|avg speed  |path/URI
======+====+===========+=======================================================
d31304|OK  |   7.3MiB/s|/opt/owncloud.zip
Status Legend:
(OK):download completed.

# 从文件中读取地址
aria2c -i test-aria2.txt
[DL:3.9MiB][#b97984 192KiB/21MiB(0%)][#673c8e 2.5MiB/3.6MiB(69%)]
03/22 10:14:22 [NOTICE] Download complete: /opt/wget-1.17.tar.gz
[#b97984 19MiB/21MiB(90%) CN:1 DL:2.5MiB]
03/22 10:14:30 [NOTICE] Download complete: /opt/owncloud-9.0.0.tar.bz2
Download Results:
gid   |stat|avg speed  |path/URI
======+====+===========+=======================================================
673c8e|OK  |   4.3MiB/s|/opt/wget-1.17.tar.gz
b97984|OK  |   2.5MiB/s|/opt/owncloud-9.0.0.tar.bz2
Status Legend:
(OK):download completed.

# 带密码下载
aria2c --http-user=xxx --http-password=xxx https://download.owncloud.org/community/owncloud-9.0.0.tar.bz2
aria2c --ftp-user=xxx --ftp-password=xxx ftp://ftp.gnu.org/gnu/wget/wget-1.17.tar.gz
```
### 配置aria2c

配置文件名称: ~/.aria2/aria2.conf

```bash
## 全局设置 ## ============================================================
# 日志
#log-level=warn
#log=/PATH/.aria2/aria2.log

# 后台运行
daemon=true

# 下载位置, 默认: 当前启动位置
dir=/PATH/Downloads

# 从会话文件中读取下载任务
input-file=/PATH/.aria2/aria2.session

# 在Aria2退出时保存`错误/未完成`的下载任务到会话文件
save-session=/PATH/.aria2/aria2.session

# 定时保存会话, 0为退出时才保存, 需1.16.1以上版本, 默认:0
save-session-interval=30

# 断点续传
continue=true

# 启用磁盘缓存, 0为禁用缓存, 需1.16以上版本, 默认:16M
#disk-cache=32M

# 文件预分配方式, 能有效降低磁盘碎片, 默认:prealloc
# 预分配所需时间: none < falloc ? trunc < prealloc
# falloc和trunc则需要文件系统和内核支持
# NTFS建议使用falloc, EXT3/4建议trunc, MAC 下需要注释此项
file-allocation=none

# 客户端伪装
user-agent=netdisk;5.2.6;PC;PC-Windows;6.2.9200;WindowsBaiduYunGuanJia
referer=http://pan.baidu.com/disk/home

# 禁用IPv6, 默认:false
disable-ipv6=true

# 其他
always-resume=true
check-integrity=true

## 下载位置 ## ============================================================
# 最大同时下载任务数, 运行时可修改, 默认:5
max-concurrent-downloads=5

# 同一服务器连接数, 添加时可指定, 默认:1
max-connection-per-server=5

# 最小文件分片大小, 添加时可指定, 取值范围1M -1024M, 默认:20M
# 假定size=10M, 文件为20MiB 则使用两个来源下载; 文件为15MiB 则使用一个来源下载
min-split-size=10M

# 单个任务最大线程数, 添加时可指定, 默认:5
split=5

# 整体下载速度限制, 运行时可修改, 默认:0
#max-overall-download-limit=0

# 单个任务下载速度限制, 默认:0
#max-download-limit=0

# 整体上传速度限制, 运行时可修改, 默认:0
#max-overall-upload-limit=0

# 单个任务上传速度限制, 默认:0
#max-upload-limit=0

## RPC设置 ## ============================================================
# 启用RPC, 默认:false
enable-rpc=true

# 允许所有来源, 默认:false
rpc-allow-origin-all=true

# 允许非外部访问, 默认:false
rpc-listen-all=true

# 事件轮询方式, 取值:[epoll, kqueue, port, poll, select], 不同系统默认值不同
#event-poll=select

# RPC监听端口, 端口被占用时可以修改, 默认:6800
rpc-listen-port=6800

# 设置的RPC授权令牌, v1.18.4新增功能, 取代 --rpc-user 和 --rpc-passwd 选项
#rpc-secret=<TOKEN>

# 是否启用 RPC 服务的 SSL/TLS 加密,
# 启用加密后 RPC 服务需要使用 https 或者 wss 协议连接
#rpc-secure=true

# 在 RPC 服务中启用 SSL/TLS 加密时的证书文件,
# 使用 PEM 格式时，您必须通过 --rpc-private-key 指定私钥
#rpc-certificate=/path/to/certificate.pem

# 在 RPC 服务中启用 SSL/TLS 加密时的私钥文件
#rpc-private-key=/path/to/certificate.key

## BT/PT下载相关 ## ============================================================
# 当下载的是一个种子(以.torrent结尾)时, 自动开始BT任务, 默认:true
#follow-torrent=true

# BT监听端口, 当端口被屏蔽时使用, 默认:6881-6999
listen-port=51413

# 单个种子最大连接数, 默认:55
#bt-max-peers=55

# 打开DHT功能, PT需要禁用, 默认:true
enable-dht=false

# 打开IPv6 DHT功能, PT需要禁用
#enable-dht6=false

# DHT网络监听端口, 默认:6881-6999
#dht-listen-port=6881-6999

dht-file-path=/opt/var/aria2/dht.dat
dht-file-path6=/opt/var/aria2/dht6.dat

# 本地节点查找, PT需要禁用, 默认:false
#bt-enable-lpd=false

# 种子交换, PT需要禁用, 默认:true
enable-peer-exchange=false

# 每个种子限速, 对少种的PT很有用, 默认:50K
#bt-request-peer-speed-limit=50K

# 设置 peer id 前缀
peer-id-prefix=-TR2770-

# 当种子的分享率达到这个数时, 自动停止做种, 0为一直做种, 默认:1.0
seed-ratio=0

# 强制保存会话, 即使任务已经完成, 默认:false
# 较新的版本开启后会在任务完成后依然保留.aria2文件
#force-save=false

# BT校验相关, 默认:true
#bt-hash-check-seed=true

# 继续之前的BT任务时, 无需再次校验, 默认:false
bt-seed-unverified=true

# 保存磁力链接元数据为种子文件(.torrent文件), 默认:false
bt-save-metadata=true

bt-max-open-files=16
```

### PS: 可以借鉴某hub的配置方式！

```bash
#
# https://github.com/P3TERX/aria2.conf
# File name：aria2.conf
# Description: Awesome Aria2 configuration file
# Version: 2021.09.15
#
# Copyright (c) 2018-2021 P3TERX <https://p3terx.com>
#
# This is free software, licensed under the MIT License.
# See /LICENSE for more information.
#

## 文件保存设置 ##

# 下载目录。可使用绝对路径或相对路径, 默认: 当前启动位置
dir=/usr/local/etc/nginxContain/data

# 磁盘缓存, 0 为禁用缓存，默认:16M
# 磁盘缓存的作用是把下载的数据块临时存储在内存中，然后集中写入硬盘，以减少磁盘 I/O ，提升读写性能，延长硬盘寿命。
# 建议在有足够的内存空闲情况下适当增加，但不要超过剩余可用内存空间大小。
# 此项值仅决定上限，实际对内存的占用取决于网速(带宽)和设备性能等其它因素。
disk-cache=64M

# 文件预分配方式, 可选：none, prealloc, trunc, falloc, 默认:prealloc
# 预分配对于机械硬盘可有效降低磁盘碎片、提升磁盘读写性能、延长磁盘寿命。
# 机械硬盘使用 ext4（具有扩展支持），btrfs，xfs 或 NTFS（仅 MinGW 编译版本）等文件系统建议设置为 falloc
# 若无法下载，提示 fallocate failed.cause：Operation not supported 则说明不支持，请设置为 none
# prealloc 分配速度慢, trunc 无实际作用，不推荐使用。
# 固态硬盘不需要预分配，只建议设置为 none ，否则可能会导致双倍文件大小的数据写入，从而影响寿命。
file-allocation=none

# 文件预分配大小限制。小于此选项值大小的文件不预分配空间，单位 K 或 M，默认：5M
no-file-allocation-limit=64M

# 断点续传
continue=true

# 始终尝试断点续传，无法断点续传则终止下载，默认：true
always-resume=false

# 不支持断点续传的 URI 数值，当 always-resume=false 时生效。
# 达到这个数值从将头开始下载，值为 0 时所有 URI 不支持断点续传时才从头开始下载。
max-resume-failure-tries=0

# 获取服务器文件时间，默认:false
remote-time=true


## 进度保存设置 ##

# 从会话文件中读取下载任务
input-file=/root/.aria2/aria2.session

# 会话文件保存路径
# Aria2 退出时或指定的时间间隔会保存`错误/未完成`的下载任务到会话文件
save-session=/root/.aria2/aria2.session

# 任务状态改变后保存会话的间隔时间（秒）, 0 为仅在进程正常退出时保存, 默认:0
# 为了及时保存任务状态、防止任务丢失，此项值只建议设置为 1
save-session-interval=1

# 自动保存任务进度到控制文件(*.aria2)的间隔时间（秒），0 为仅在进程正常退出时保存，默认：60
# 此项值也会间接影响从内存中把缓存的数据写入磁盘的频率
# 想降低磁盘 IOPS (每秒读写次数)则提高间隔时间
# 想在意外非正常退出时尽量保存更多的下载进度则降低间隔时间
# 非正常退出：进程崩溃、系统崩溃、SIGKILL 信号、设备断电等
auto-save-interval=20

# 强制保存，即使任务已完成也保存信息到会话文件, 默认:false
# 开启后会在任务完成后保留 .aria2 文件，文件被移除且任务存在的情况下重启后会重新下载。
# 关闭后已完成的任务列表会在重启后清空。
force-save=false


## 下载连接设置 ##

# 文件未找到重试次数，默认:0 (禁用)
# 重试时同时会记录重试次数，所以也需要设置 max-tries 这个选项
max-file-not-found=10

# 最大尝试次数，0 表示无限，默认:5
max-tries=0

# 重试等待时间（秒）, 默认:0 (禁用)
retry-wait=15

# 连接超时时间（秒）。默认：60
connect-timeout=10

# 超时时间（秒）。默认：60
timeout=10

# 最大同时下载任务数, 运行时可修改, 默认:5
max-concurrent-downloads=10

# 单服务器最大连接线程数, 任务添加时可指定, 默认:1
# 最大值为 16 (增强版无限制), 且受限于单任务最大连接线程数(split)所设定的值。
max-connection-per-server=16

# 单任务最大连接线程数, 任务添加时可指定, 默认:5
split=64

# 文件最小分段大小, 添加时可指定, 取值范围 1M-1024M (增强版最小值为 1K), 默认:20M
# 比如此项值为 10M, 当文件为 20MB 会分成两段并使用两个来源下载, 文件为 15MB 则只使用一个来源下载。
# 理论上值越小使用下载分段就越多，所能获得的实际线程数就越大，下载速度就越快，但受限于所下载文件服务器的策略。
min-split-size=4M

# HTTP/FTP 下载分片大小，所有分割都必须是此项值的倍数，最小值为 1M (增强版为 1K)，默认：1M
piece-length=1M

# 允许分片大小变化。默认：false
# false：当分片大小与控制文件中的不同时将会中止下载
# true：丢失部分下载进度继续下载
allow-piece-length-change=true

# 最低下载速度限制。当下载速度低于或等于此选项的值时关闭连接（增强版本为重连），此选项与 BT 下载无关。单位 K 或 M ，默认：0 (无限制)
lowest-speed-limit=0

# 全局最大下载速度限制, 运行时可修改, 默认：0 (无限制)
max-overall-download-limit=0

# 单任务下载速度限制, 默认：0 (无限制)
max-download-limit=0

# 禁用 IPv6, 默认:false
disable-ipv6=true

# GZip 支持，默认:false
http-accept-gzip=true

# URI 复用，默认: true
reuse-uri=false

# 禁用 netrc 支持，默认:false
no-netrc=true

# 允许覆盖，当相关控制文件(.aria2)不存在时从头开始重新下载。默认:false
allow-overwrite=false

# 文件自动重命名，此选项仅在 HTTP(S)/FTP 下载中有效。新文件名在名称之后扩展名之前加上一个点和一个数字（1..9999）。默认:true
auto-file-renaming=true

# 使用 UTF-8 处理 Content-Disposition ，默认:false
content-disposition-default-utf8=true

# 最低 TLS 版本，可选：TLSv1.1、TLSv1.2、TLSv1.3 默认:TLSv1.2
#min-tls-version=TLSv1.2


## BT/PT 下载设置 ##

# BT 监听端口(TCP), 默认:6881-6999
# 直通外网的设备，比如 VPS ，务必配置防火墙和安全组策略允许此端口入站
# 内网环境的设备，比如 NAS ，除了防火墙设置，还需在路由器设置外网端口转发到此端口
listen-port=51413

# DHT 网络与 UDP tracker 监听端口(UDP), 默认:6881-6999
# 因协议不同，可以与 BT 监听端口使用相同的端口，方便配置防火墙和端口转发策略。
dht-listen-port=51413

# 启用 IPv4 DHT 功能, PT 下载(私有种子)会自动禁用, 默认:true
enable-dht=true

# 启用 IPv6 DHT 功能, PT 下载(私有种子)会自动禁用，默认:false
# 在没有 IPv6 支持的环境开启可能会导致 DHT 功能异常
enable-dht6=false

# 指定 BT 和 DHT 网络中的 IP 地址
# 使用场景：在家庭宽带没有公网 IP 的情况下可以把 BT 和 DHT 监听端口转发至具有公网 IP 的服务器，在此填写服务器的 IP ，可以提升 BT 下载速率。
#bt-external-ip=

# IPv4 DHT 文件路径，默认：$HOME/.aria2/dht.dat
dht-file-path=/root/.aria2/dht.dat

# IPv6 DHT 文件路径，默认：$HOME/.aria2/dht6.dat
dht-file-path6=/root/.aria2/dht6.dat

# IPv4 DHT 网络引导节点
dht-entry-point=dht.transmissionbt.com:6881

# IPv6 DHT 网络引导节点
dht-entry-point6=dht.transmissionbt.com:6881

# 本地节点发现, PT 下载(私有种子)会自动禁用 默认:false
bt-enable-lpd=true

# 指定用于本地节点发现的接口，可能的值：接口，IP地址
# 如果未指定此选项，则选择默认接口。
#bt-lpd-interface=

# 启用节点交换, PT 下载(私有种子)会自动禁用, 默认:true
enable-peer-exchange=true

# BT 下载最大连接数（单任务），运行时可修改。0 为不限制，默认:55
# 理想情况下连接数越多下载越快，但在实际情况是只有少部分连接到的做种者上传速度快，其余的上传慢或者不上传。
# 如果不限制，当下载非常热门的种子或任务数非常多时可能会因连接数过多导致进程崩溃或网络阻塞。
# 进程崩溃：如果设备 CPU 性能一般，连接数过多导致 CPU 占用过高，因资源不足 Aria2 进程会强制被终结。
# 网络阻塞：在内网环境下，即使下载没有占满带宽也会导致其它设备无法正常上网。因远古低性能路由器的转发性能瓶颈导致。
bt-max-peers=128

# BT 下载期望速度值（单任务），运行时可修改。单位 K 或 M 。默认:50K
# BT 下载速度低于此选项值时会临时提高连接数来获得更快的下载速度，不过前提是有更多的做种者可供连接。
# 实测临时提高连接数没有上限，但不会像不做限制一样无限增加，会根据算法进行合理的动态调节。
bt-request-peer-speed-limit=10M

# 全局最大上传速度限制, 运行时可修改, 默认:0 (无限制)
# 设置过低可能影响 BT 下载速度
max-overall-upload-limit=2M

# 单任务上传速度限制, 默认:0 (无限制)
max-upload-limit=0

# 最小分享率。当种子的分享率达到此选项设置的值时停止做种, 0 为一直做种, 默认:1.0
# 强烈建议您将此选项设置为大于等于 1.0
seed-ratio=1.0

# 最小做种时间（分钟）。设置为 0 时将在 BT 任务下载完成后停止做种。
seed-time=0

# 做种前检查文件哈希, 默认:true
bt-hash-check-seed=true

# 继续之前的BT任务时, 无需再次校验, 默认:false
bt-seed-unverified=false

# BT tracker 服务器连接超时时间（秒）。默认：60
# 建立连接后，此选项无效，将使用 bt-tracker-timeout 选项的值
bt-tracker-connect-timeout=10

# BT tracker 服务器超时时间（秒）。默认：60
bt-tracker-timeout=10

# BT 服务器连接间隔时间（秒）。默认：0 (自动)
#bt-tracker-interval=0

# BT 下载优先下载文件开头或结尾
bt-prioritize-piece=head=32M,tail=32M

# 保存通过 WebUI(RPC) 上传的种子文件(.torrent)，默认:true
# 所有涉及种子文件保存的选项都建议开启，不保存种子文件有任务丢失的风险。
# 通过 RPC 自定义临时下载目录可能不会保存种子文件。
rpc-save-upload-metadata=true

# 下载种子文件(.torrent)自动开始下载, 默认:true，可选：false|mem
# true：保存种子文件
# false：仅下载种子文件
# mem：将种子保存在内存中
follow-torrent=true

# 种子文件下载完后暂停任务，默认：false
# 在开启 follow-torrent 选项后下载种子文件或磁力会自动开始下载任务进行下载，而同时开启当此选项后会建立相关任务并暂停。
pause-metadata=false

# 保存磁力链接元数据为种子文件(.torrent), 默认:false
bt-save-metadata=true

# 加载已保存的元数据文件(.torrent)，默认:false
bt-load-saved-metadata=true

# 删除 BT 下载任务中未选择文件，默认:false
bt-remove-unselected-file=true

# BT强制加密, 默认: false
# 启用后将拒绝旧的 BT 握手协议并仅使用混淆握手及加密。可以解决部分运营商对 BT 下载的封锁，且有一定的防版权投诉与迅雷吸血效果。
# 此选项相当于后面两个选项(bt-require-crypto=true, bt-min-crypto-level=arc4)的快捷开启方式，但不会修改这两个选项的值。
bt-force-encryption=true

# BT加密需求，默认：false
# 启用后拒绝与旧的 BitTorrent 握手协议(\19BitTorrent protocol)建立连接，始终使用混淆处理握手。
#bt-require-crypto=true

# BT最低加密等级，可选：plain（明文），arc4（加密），默认：plain
#bt-min-crypto-level=arc4

# 分离仅做种任务，默认：false
# 从正在下载的任务中排除已经下载完成且正在做种的任务，并开始等待列表中的下一个任务。
bt-detach-seed-only=true


## 客户端伪装 ##

# 自定义 User Agent
user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36 Edg/93.0.961.47

# BT 客户端伪装
# PT 下载需要保持 user-agent 和 peer-agent 两个参数一致
# 部分 PT 站对 Aria2 有特殊封禁机制，客户端伪装不一定有效，且有封禁账号的风险。
#user-agent=Deluge 1.3.15
peer-agent=Deluge 1.3.15
peer-id-prefix=-DE13F0-


## 执行额外命令 ##

# 下载停止后执行的命令
# 从 正在下载 到 删除、错误、完成 时触发。暂停被标记为未开始下载，故与此项无关。
on-download-stop=/root/.aria2/delete.sh

# 下载完成后执行的命令
# 此项未定义则执行 下载停止后执行的命令 (on-download-stop)
on-download-complete=/root/.aria2/clean.sh

# 下载错误后执行的命令
# 此项未定义则执行 下载停止后执行的命令 (on-download-stop)
#on-download-error=

# 下载暂停后执行的命令
#on-download-pause=

# 下载开始后执行的命令
#on-download-start=

# BT 下载完成后执行的命令
#on-bt-download-complete=


## RPC 设置 ##

# 启用 JSON-RPC/XML-RPC 服务器, 默认:false
enable-rpc=true

# 接受所有远程请求, 默认:false
rpc-allow-origin-all=true

# 允许外部访问, 默认:false
rpc-listen-all=true

# RPC 监听端口, 默认:6800
rpc-listen-port=6800

# RPC 密钥
rpc-secret=P3TERX

# RPC 最大请求大小
rpc-max-request-size=10M

# RPC 服务 SSL/TLS 加密, 默认：false
# 启用加密后必须使用 https 或者 wss 协议连接
# 不推荐开启，建议使用 web server 反向代理，比如 Nginx、Caddy ，灵活性更强。
#rpc-secure=false

# 在 RPC 服务中启用 SSL/TLS 加密时的证书文件(.pem/.crt)
#rpc-certificate=/root/.aria2/xxx.pem

# 在 RPC 服务中启用 SSL/TLS 加密时的私钥文件(.key)
#rpc-private-key=/root/.aria2/xxx.key

# 事件轮询方式, 可选：epoll, kqueue, port, poll, select, 不同系统默认值不同
#event-poll=select


## 高级选项 ##

# 启用异步 DNS 功能。默认：true
#async-dns=true

# 指定异步 DNS 服务器列表，未指定则从 /etc/resolv.conf 中读取。
#async-dns-server=8.8.8.8,1.1.1.1

# 指定单个网络接口，可能的值：接口，IP地址，主机名
# 如果接口具有多个 IP 地址，则建议指定 IP 地址。
# 已知指定网络接口会影响依赖本地 RPC 的连接的功能场景，即通过 localhost 和 127.0.0.1 无法与 Aria2 服务端进行讯通。
#interface=

# 指定多个网络接口，多个值之间使用逗号(,)分隔。
# 使用 interface 选项时会忽略此项。
#multiple-interface=


## 日志设置 ##

# 日志文件保存路径，忽略或设置为空为不保存，默认：不保存
#log=

# 日志级别，可选 debug, info, notice, warn, error 。默认：debug
#log-level=warn

# 控制台日志级别，可选 debug, info, notice, warn, error ，默认：notice
console-log-level=notice

# 安静模式，禁止在控制台输出日志，默认：false
quiet=false

# 下载进度摘要输出间隔时间（秒），0 为禁止输出。默认：60
summary-interval=0


## 增强扩展设置(非官方) ##

# 仅适用于 myfreeer/aria2-build-msys2 (Windows) 和 P3TERX/Aria2-Pro-Core (GNU/Linux) 项目所构建的增强版本

# 在服务器返回 HTTP 400 Bad Request 时重试，仅当 retry-wait > 0 时有效，默认 false
#retry-on-400=true

# 在服务器返回 HTTP 403 Forbidden 时重试，仅当 retry-wait > 0 时有效，默认 false
#retry-on-403=true

# 在服务器返回 HTTP 406 Not Acceptable 时重试，仅当 retry-wait > 0 时有效，默认 false
#retry-on-406=true

# 在服务器返回未知状态码时重试，仅当 retry-wait > 0 时有效，默认 false
#retry-on-unknown=true

# 是否发送 Want-Digest HTTP 标头。默认：false (不发送)
# 部分网站会把此标头作为特征来检测和屏蔽 Aria2
#http-want-digest=false


## BitTorrent trackers ##
bt-tracker=
```

## 宿主与容器覆盖规则

```bash
若容器目录为普通目录，则宿主一定覆盖容器目录！

若容器目录为工作目录，宿主空，则：容器覆盖宿主；宿主非空，则：宿主覆盖容器！

疑惑点一：nginx容器内/usr/share/nginx/html/按理说是工作目录，宿主空，则容器覆盖宿主，实际上确实宿主都会覆盖容器！(除非不是工作目录。)
```

## 测速

- 其他人点击网页测速.

```bash
docker run --name openspeedtest --rm -d -p 3000:3000 -p 3001:3001 openspeedtest/latest
```

- 主机自测速

```bash
# 三网测速
bash <(curl -Lso- https://down.wangchao.info/sh/superspeed/superspeed.sh)

# VPS综合测试
bash <(wget -qO- https://down.vpsaff.net/linux/speedtest/superbench.sh)

# 三线测速
bash <(wget -qO- https://bench.im/hyperspeed)
```

## ffmpeg合并音频和视频

```bash
ffmpeg -i 01.mp3 -i 01.mp4 -y 01_combine.mp4
```

## yt-dlp下载

```bash
nohup yt-dlp -P asianalways -o "%(title)s.mp4" --concurrent-fragments 16 https://cn.video.com/model/asianalways &
```

## 多线程打包(不推荐)

```bash
# 多线程打包：
## 打包&解压文件
pigz -k your_file_name
unpigz -d your_file_name.gz

## 文件夹压缩
tar -cvf - dir1 dir2 dir3 | pigz > output.tar.gz

## 文件夹解压缩,会删除源.
pigz -d output.tar.gz

## 文件夹压缩
zip -r Beauty3D | pigz > output.tar.gz
```

## node-sass

```bash
# cnpm可以辅助安装package.
npm install -g cnpm --registry=https://registry.npm.taobao.org
cnpm -v

# 配置npm镜像
npm config set registry https://registry.npm.taobao.org/
npm config set registry https://registry.npmjs.org/

# 确认是否真的修改.
npm config get registry

# 指定路径安装npm包.
npm install node-sass@4.13.1 --registry=https://registry.npmjs.org/

# work for install npm-sass
npm install --unsafe-perm node-sass
```

## 多环境配置-使用具体某一环境方法

```bash
java -java spring*.jav --spring.profiles.active=test
java -java spring*.jav --server.port=88 --spring.profiles.active=test
```

## 时间校准

```bash
tzselect

sudo ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
```