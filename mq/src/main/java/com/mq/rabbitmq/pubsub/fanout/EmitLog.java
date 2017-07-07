package com.mq.rabbitmq.pubsub.fanout;

import com.mq.rabbitmq.RabbitMqConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description Fanout Exchange 不管路由键或者是路由模式，_会把消息发给绑定给它的全部队列_。如果不同的consumer需要对同样的消息进行不同的处理，那么这种方式是很有用的。
 * @Author xuedong.wang
 * @Date 17/7/7.
 */
public class EmitLog {

    private final static String EXCHANGE_NAME = "logs";

    private static String getMessage(String[] strings){
        if (strings.length < 1)
            return "Hello World! ".concat(String.valueOf(Math.random()));
        return strings.toString();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitMqConfig.HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        try {
            String message = getMessage(args);
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } finally {
            channel.close();
            connection.close();
        }
    }
}
