package com.mq.service.kafka;

/**
 * @Description 测试 yxp_cl 分组
 * @Author xuedong.wang
 * @Date 17/5/9.
 */
public class ConsumerYxp_cl {

    public static void main(String[] arg) {
        String[] args = {"192.168.201.227:2181", "yxp_cl", "checklist", "1"};
        String zooKeeper = args[0];
        String groupId = args[1];
        String topic = args[2];
        int threads = Integer.parseInt(args[3]);

        ConsumerDemo demo = new ConsumerDemo(zooKeeper, groupId, topic);
        demo.run(threads);

        try {
            Thread.sleep(100000);
        } catch (InterruptedException ie) {

        }
        demo.shutdown();


    }
}
