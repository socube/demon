package com.mq.rabbitmq.pubsub.direct;

import com.mq.rabbitmq.RabbitMqConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.concurrent.TimeoutException;

/**
 * @Description Direct Exchange _主要根据 路由键 来分发消息到不同的队列中_。路由键是消息的一个属性，由生产者加在消息头中
 * @Author xuedong.wang
 * @Date 17/7/7.
 */
public class EmitLogDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws java.io.IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitMqConfig.HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        int level = new Double(Math.random() * 1000000.0d).intValue() % 3;

        String severity = "info";
        switch (level) {
            case 1:
                severity = "warning";
                break;
            case 2:
                severity = "error";
                break;
        }
        String message = new String("[" + severity + "] " + "->message");

        try {
            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
        } finally {
            channel.close();
            connection.close();
        }
    }
}
