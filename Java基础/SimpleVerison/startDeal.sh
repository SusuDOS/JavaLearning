# 下载处理脚本
# kill -9 $(lsof -t -i:51413)
# nohup aria2c -c *.torrent &
# find . -name "target" | xargs rm -rf
# http://smilecat.gaoshuye.top:48099/001.zip
# Referer:https://v8cdn.snmovie.com
# docker run -d --rm --name nginxHTTP -p 48099:80  -v /usr/local/etc/nginxContain/data/:/usr/share/nginx/html/ nginx:stable
# docker run -d --rm --name nginx -p 48099:80  -v /usr/local/etc/nginx/data/:/usr/share/nginx/html/ nginx:stable
# bash <(curl -Lso- https://down.wangchao.info/sh/superspeed/superspeed.sh)
mkdir -p /usr/local/etc/nginxContain/video/

# 1. 移动文件
find . -name "*.mp4" -exec mv {} /usr/local/etc/nginxContain/video/ \;
find . -name "*.TS" -exec mv {} /usr/local/etc/nginxContain/video/ \;
find . -name "*.avi" -exec mv {} /usr/local/etc/nginxContain/video/ \;

# 2. 清除小文件
find ./ -size -60M -exec rm {} \;


# 3. 压缩打包
ls | awk '{ print "zip -r -P'ghp_6nInw32QLp' "$0".zip " $0|"/bin/bash" }'
ls | awk '{ print "zip -r "$0".zip " $0|"/bin/bash" }'

# 手动
nohup zip -9r -P'123456b' compress.zip temp14 &


