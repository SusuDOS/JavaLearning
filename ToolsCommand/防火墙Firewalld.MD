# Firewalld配置

## 状态查看

```bash
#查看firewalld.service服务状态
systemctl status firewalld

#查看firewall运行状态
firewall-cmd --state

#查看当前所有规则
firewall-cmd --list-all

#单独查看端口白名单列表
firewall-cmd --zone=public --list-ports
```

## 配置端口

```bash
# 查询端口是否开放
firewall-cmd --query-port=52005/tcp

# 新建永久规则，开放52005端口（TCP协议）,任何ip都可以访问
firewall-cmd --permanent --add-port=51413/tcp
firewall-cmd --permanent --add-port=51413/udp

# 移除上述规则
firewall-cmd --permanent --remove-port=52005/tcp
# 新建永久规则，批量开放一段端口（TCP协议）
firewall-cmd --permanent --add-port=9001-9100/tcp

#添加或者移除规则后重新加载firewall后配置才会生效
firewall-cmd --reload
```

## 配置ip

```bash
# 新建永久规则，开放192.168.1.1单个源IP的访问
firewall-cmd --permanent --add-source=192.168.1.1

# 新建永久规则，开放192.168.1.0/24整个源IP段的访问
firewall-cmd --permanent --add-source=192.168.1.0/24

# 移除上述规则
firewall-cmd --permanent --remove-source=192.168.1.1
```
## 复杂规则

```bash
# 允许指定IP访问本机8080端口
firewall-cmd --permanent --add-rich-rule='rule family="ipv4" source address="192.168.1.1" port protocol="tcp" port="8080" accept'

# 允许指定IP段访问本机8080-8090端口
firewall-cmd --permanent --add-rich-rule='rule family="ipv4" source address="192.168.1.0/24" port protocol="tcp" port="8080-8090" accept'

# 禁止指定IP访问本机8080端口
firewall-cmd --permanent --add-rich-rule='rule family="ipv4" source address="192.168.1.1" port protocol="tcp" port="8080" reject'

#移除第一条规则(所有的移除规则基本都是add改成remove)
firewall-cmd --permanent --remove-rich-rule='rule family="ipv4" source address="192.168.1.1" port protocol="tcp" port="8080" accept'
```

## 服务规则

```bash
# 使mysql服务的3306端口只允许192.168.1.1/24网段的服务器能访问
firewall-cmd --permanent --add-rich-rule="rule family="ipv4" source address="192.168.1.1/24" port protocol="tcp" port="3306" accept"

#reload使生效
firewall-cmd --reload
```