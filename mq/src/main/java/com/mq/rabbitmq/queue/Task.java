package com.mq.rabbitmq.queue;

import com.mq.rabbitmq.RabbitMqConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/7/7.
 */
public class Task {

    private final static String QUEUE_NAME = "task_queue";

    private static String getMessage(String[] strings){
        if (strings.length < 1)
            return "Hello World task_queue ! ".concat(String.valueOf(Math.random()));
        return strings.toString();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitMqConfig.HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        try {
            String message = getMessage(args);
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } finally {
            channel.close();
            connection.close();
        }
    }
}
