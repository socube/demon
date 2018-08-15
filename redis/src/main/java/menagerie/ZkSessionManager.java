package menagerie;

import org.apache.zookeeper.ZooKeeper;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/30.
 */
public interface ZkSessionManager {

    /**
     * Gets the ZooKeeper client object to use.
     * <p>
     * This method should always return a ZooKeeper object that is neither expired nor closed. In the case
     * of connection failure (due to network partitions, etc), then this object is not necessarily in a valid state,
     * but it should never be in an expired state.
     * <p>
     * If this session manager object has been closed via a call to {@link #closeSession()}, then an
     * IllegalStateException will be thrown.
     *
     * @return a ZooKeeper instance to use
     * @throws IllegalStateException if this SessionManager has been closed
     * @throws RuntimeException if a connection problem occurs with the ZooKeeper service
     */
    ZooKeeper getZooKeeper();

    /**
     * Closes this Session, and renders all future calls to {@link #getZooKeeper()} invalid.
     */
    void closeSession();

    /**
     * Add a connection listener to listen for connection events.
     *
     * @param listener the connection listener to add
     */
    void addConnectionListener(ConnectionListener listener);

    /**
     * Remove the specified connection listener from the session manager.
     *
     * @param listener the listener to be removed.
     */
    void removeConnectionListener(ConnectionListener listener);
}
