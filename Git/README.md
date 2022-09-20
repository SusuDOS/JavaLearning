git版本控制，由于idea版本的不一致可能存在入口不一致的可能性，但是可以直接使用git命令行操作亦可.

主要在于分支的创建，冲突代码的合并问题.

## 常用三连

```bash
# 配置信息
git config --global user.email "wd32941****@163.com"
git config --global user.name "SusuDOS"

# 删除本地标签
git tag -d v0.1

# # 删除远程标签
git push origin :refs/tags/v0.2

# 添加本地标签
git tag -a v0.2 -m "version 0.2"

# 推送标签到远程
git push origin v0.2
```