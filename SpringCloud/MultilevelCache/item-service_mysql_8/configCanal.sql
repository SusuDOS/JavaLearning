-- 创建用户canal，将变化的表数值推送到其他缓存结构.
-- mysql5.7

-- create user canal@'%' IDENTIFIED by 'canal';
-- GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT,SUPER ON *.* TO 'canal'@'%' identified by 'canal';
-- FLUSH PRIVILEGES;


-- mysql8.0
create user 'canal'@'%' IDENTIFIED by 'canal';
GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT,SUPER ON *.* TO 'canal'@'%';
FLUSH PRIVILEGES;