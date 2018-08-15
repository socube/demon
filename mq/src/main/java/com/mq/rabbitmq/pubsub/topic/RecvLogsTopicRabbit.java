package com.mq.rabbitmq.pubsub.topic;

import com.mq.rabbitmq.RabbitMqConfig;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/7/7.
 */
public class RecvLogsTopicRabbit {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitMqConfig.HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        try {
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            String queueName = channel.queueDeclare().getQueue();

            String bindingKey = "*.*.rabbit";
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);


            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
                }
            };
            channel.basicConsume(queueName, true, consumer);
            while(true) {}
        } finally {
            channel.close();
            connection.close();
        }
    }
}
