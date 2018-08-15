package zk.masterwork;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/30.
 */
public class Master implements Watcher, Runnable {

    private ZooKeeper zk;

    private String connectString;

    private String serverId;

    private static final String MASTER_PATH = "/master";

    public Master(String connectString, String serverId) {
        this.connectString = connectString;
        this.serverId = serverId;
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
    }

    public void startZK() {
        try {
            zk = new ZooKeeper(connectString, 2000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopZK() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createMaterNode() {
        try {
            zk.create(MASTER_PATH, serverId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkForMaster() {

        Stat stat = new Stat();
        byte[] data = null;
        try {
            data = zk.getData(MASTER_PATH, false, stat);
            return serverId.equals(new String(data));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Boolean.FALSE;
    }

    public boolean registerForMaster() {

        boolean isLeader = false;
        while (true) {
            if (!checkForMaster()) {
                createMaterNode();
                sleep(5);
            } else {
                isLeader = true;
                log(" master registered with ");
                break;
            }
        }
        return isLeader;
    }


    @Override
    public void run() {

        startZK();

        boolean isLeader = registerForMaster();
        if (isLeader) {
            stopZK();
        }

    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void log(String msg) {
        System.out.println(String.format("serverId %s %s", serverId, msg));
    }

    public static void main(String[] args) throws InterruptedException {
        int masterCount = 3;
        ExecutorService service = Executors.newFixedThreadPool(masterCount);
        for (int i = 0; i < masterCount; i++) {
            Master master = new Master("localhost:2181", "o2-" + i);
            service.submit(master);
        }

    }

}
