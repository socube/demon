package com.mq.rabbitmq.pubsub.topic;

import com.mq.rabbitmq.RabbitMqConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Description _Topic Exchange 转发消息主要是根据通配符。_ 在这种交换机下，队列和交换机的绑定会定义一种路由模式，那么，通配符就要在这种路由模式和路由键之间匹配后交换机才能转发消息。
 * <p>
 * 在这种交换机模式下：
 * <p>
 * 路由键必须是一串字符，用句号（.） 隔开，比如说 agreements.us，或者 agreements.eu.stockholm 等。
 * 路由模式必须包含一个 星号（），主要用于匹配路由键指定位置的一个单词，比如说，一个路由模式是这样子：agreements..b.*，那么就只能匹配路由键是这样子的：
 * 第一个单词是 agreements，第四个单词是 b。 井号（#）就表示相当于一个或者多个单词，例如一个匹配模式是agreements.eu.berlin.#，那么，以agreements.eu.berlin开头的路由键都是可以的。
 * @Author xuedong.wang
 * @Date 17/7/7.
 */
public class EmitLogTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitMqConfig.HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        //<speed>.<colour>.<species>
        String speed = "lazy";
        if (new Double(Math.random() * 1000000.0d).intValue() % 2 == 1) {
            speed = "quick";
        }

        String colour = "white";
        switch (new Double(Math.random() * 1000000.0d).intValue() % 5) {
            case 1:
                colour = "yellow";
                break;
            case 2:
                colour = "orange";
                break;
            case 3:
                colour = "red";
                break;
            case 4:
                colour = "purple";
                break;
        }

        String species = "elephant";
        switch (new Double(Math.random() * 1000000.0d).intValue() % 5) {
            case 1:
                species = "buffalo";
                break;
            case 2:
                species = "ram";
                break;
            case 3:
                species = "rabbit";
                break;
            case 4:
                species = "hamster";
                break;
        }

        try {
            String routingKey = new String("." + speed + colour + species);
            String message = new String("[" + routingKey + "] message");

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
        } finally {
            channel.close();
            connection.close();
        }
    }
}
