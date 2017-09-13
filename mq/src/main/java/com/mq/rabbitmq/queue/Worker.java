package com.mq.rabbitmq.queue;

import com.mq.rabbitmq.RabbitMqConfig;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/7/7.
 */
public class Worker {

    private final static String QUEUE_NAME = "resume_data_manager";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitMqConfig.HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        try {
            boolean durable = true;
            channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            int prefetchCount = 1;
            channel.basicQos(prefetchCount);

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                    try {
                        doWork(message);
                    } finally {
                        System.out.println(" [x] Done");
//						channel.basicAck(envelope.getDeliveryTag(), true);
                    }
                }
            };
            channel.basicConsume(QUEUE_NAME, true, consumer);
            while(true) {}
        } finally {
            channel.close();
            connection.close();
        }
    }

    private static void doWork(String task) /*throws InterruptedException*/ {
        for (char ch: task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
//					e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
