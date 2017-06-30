package zk.masterwork;

import java.io.IOException;

import org.apache.zookeeper.AsyncCallback.DataCallback;
import org.apache.zookeeper.AsyncCallback.StringCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/30.
 */
public class AsynCreate {

    /**
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zookeeper = new ZooKeeper("localhost:2181", 200000, null);

        zookeeper.create("/mas", "sid-o2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,
                new StringCallback() {

                    @Override
                    public void processResult(int rc, String path, Object ctx, String name) {
                        Code code = Code.get(rc);
                        switch (code) {
                            case OK:
                                System.out.println(code);
                                break;
                            case NODEEXISTS:
                                System.out.println(code);
                                break;
                            case SESSIONEXPIRED:
                                System.out.println(code);
                                break;
                            default:
                                System.out.println("unknow " + code);
                        }

                    }
                }, null);

        DataCallback callback = new DataCallback() {

            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                Code code = Code.get(rc);
                System.out.println("code for check " + code);
                switch (code) {
                    case OK:
                        break;
                    case NONODE:
                        break;
                    case NODEEXISTS:
                        break;
                    case SESSIONEXPIRED:
                        break;
                    default:
                }

            }

        };

        zookeeper.getData("/mas", true, callback, null);

        Thread.sleep(200000);

    }

}
