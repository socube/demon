package zk.code;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/29.
 */
public interface DataMonitorListener {

    /**
     * The existence status of the node has changed.
     */
    void exists(byte data[]);

    /**
     * The ZooKeeper session is no longer valid.
     *
     * @param rc the ZooKeeper reason code
     */
    void closing(int rc);
}
