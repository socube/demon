package zk.masterwork;

import org.apache.zookeeper.AsyncCallback.ChildrenCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/30.
 */
public class ChildrenCallbackMonitor {

    /**
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final ZooKeeper zookeeper = new ZooKeeper("localhost:2181", 2000, null);

        final ChildrenCallback callback = new ChildrenCallback() {

            @Override
            public void processResult(int rc, String path, Object ctx, List<String> children) {
                System.out.println(children);

            }

        };

        Watcher watcher = new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                System.out.println("Event is " + event);
                if (event.getType() == EventType.NodeChildrenChanged) {
                    System.out.println("Changed " + event);
                    zookeeper.getChildren("/workers", this, callback, null);
                }
            }
        };

        zookeeper.getChildren("/workers", watcher, callback, null);

        System.out.println("begin finish");
        Thread.sleep(200000);
        System.out.println("finish");

    }

}
