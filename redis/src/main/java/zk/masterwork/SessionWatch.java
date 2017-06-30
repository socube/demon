package zk.masterwork;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/30.
 */
public class SessionWatch {
    /**
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zookeeper = new ZooKeeper("localhost:2181", 2000, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                System.out.println("Event is " + event);
            }

        });


        Thread.sleep(200000);

    }
}
