# 特别说明

第一次修改完成的可以正常Work,mysql为5.7；第二次试图升级mysql8.0.30版本，但是无论是mysql的5.7还是8.0.30，canal都无法正常运行，canal exited code 139,搜索资料似乎是docker的问题，有人docker version 15升级到20也出现过该问题,具体原因待定....

所以：第二次都没有跑通.

经在腾讯224上可以正常work,推定可能是ubuntu18.04LTS问题，重新安装了centos7.9版本后正常运行,只测试了`test-docker-compose.yml` 。