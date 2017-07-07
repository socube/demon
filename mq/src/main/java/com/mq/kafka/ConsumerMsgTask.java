package com.mq.kafka;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
/**
 * @Description 消息处理类
 * @Author xuedong.wang
 * @Date 17/3/3.
 */
public class ConsumerMsgTask implements Runnable {


    private KafkaStream m_stream;
    private int m_threadNumber;

    public ConsumerMsgTask(KafkaStream stream, int threadNumber) {
        m_threadNumber = threadNumber;
        m_stream = stream;
    }

    public void run() {
        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
        while (it.hasNext())
            System.out.println("Thread " + m_threadNumber + ": "
                    + new String(it.next().message()));
        System.out.println("Shutting down Thread: " + m_threadNumber);
    }
}
