docker network delete mq-net

docker network create mq-net

docker run -d --rm --net mq-net \
-v /root/mq-advanced/rabbitMQ/rabbitmq1/rabbitmq.conf:/etc/rabbitmq/rabbitmq.conf \
-v /root/mq-advanced/rabbitMQ/rabbitmq1/.erlang.cookie:/var/lib/rabbitmq/.erlang.cookie \
-e RABBITMQ_DEFAULT_USER=itcast \
-e RABBITMQ_DEFAULT_PASS=123321 \
--name rabbit1 \
--hostname host1 \
-p 8071:5672 \
-p 8081:15672 \
rabbitmq:3.10-management

docker run -d --rm --net mq-net \
-v /root/mq-advanced/rabbitMQ/rabbitmq1/rabbitmq.conf:/etc/rabbitmq/rabbitmq.conf \
-v /root/mq-advanced/rabbitMQ/rabbitmq1/.erlang.cookie:/var/lib/rabbitmq/.erlang.cookie \
-e RABBITMQ_DEFAULT_USER=itcast \
-e RABBITMQ_DEFAULT_PASS=123321 \
--name rabbit2 \
--hostname host2 \
-p 8072:5672 \
-p 8082:15672 \
rabbitmq:3.10-management

docker run -d --rm --net mq-net \
-v /root/mq-advanced/rabbitMQ/rabbitmq1/rabbitmq.conf:/etc/rabbitmq/rabbitmq.conf \
-v /root/mq-advanced/rabbitMQ/rabbitmq1/.erlang.cookie:/var/lib/rabbitmq/.erlang.cookie \
-e RABBITMQ_DEFAULT_USER=itcast \
-e RABBITMQ_DEFAULT_PASS=123321 \
--name rabbit3 \
--hostname host3 \
-p 8073:5672 \
-p 8083:15672 \
rabbitmq:3.10-management

