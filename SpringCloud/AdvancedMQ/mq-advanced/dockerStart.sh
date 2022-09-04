docker run -d --hostname rabbit --name rabbit \
-e RABBITMQ_DEFAULT_USER=itcast -e RABBITMQ_DEFAULT_PASS=123321 \
-p 15672:15672 -p 5672:5672 \
-v /root/mq-advanced/RabbitMQPlugin/rabbitmq_delayed_message_exchange-3.10.2.ez:/plugins/rabbitmq_delayed_message_exchange-3.10.2.ez \
rabbitmq:3.10-management