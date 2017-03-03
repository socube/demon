package zkLock;


import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/1/8.
 */
public class ZkDistributedLock {

    private static final int SESSION_TIMEOUT = 10000;  //TODO 添加日志

    private static final int DEFAULT_TIMEOUT_PERIOD = 10000;

    private static final String CONNECTION_STRING = "192.168.201.218:2181";

    private static final byte[] data = {0x12, 0x34};

    private ZooKeeper zookeeper;

    private String root;

    private String id;

    private String idName;  //TODO 可以删除

    private String ownerId;

    private String lastChildId;

    private Throwable other = null;

    private KeeperException exception = null;

    private InterruptedException interrupt = null;

    public ZkDistributedLock(String root) {
        try {
            this.zookeeper = new ZooKeeper(CONNECTION_STRING, SESSION_TIMEOUT, null);
            this.root = root;
            ensureExists(root);
        } catch (IOException e) {
            e.printStackTrace();
            other = e;
        }
    }


    /**
     * 尝试获取锁操作，阻塞式可被中断
     */
    public void lock() throws Exception {
        // 可能初始化的时候就失败了
        if (exception != null) {
            throw exception;
        }

        if (interrupt != null) {
            throw interrupt;
        }

        if (other != null) {
            throw new Exception("", other);
        }

        if (isOwner()) {// 锁重入
            return;
        }

        BooleanMutex mutex = new BooleanMutex();
        acquireLock(mutex);
        // 避免zookeeper重启后导致watcher丢失，会出现死锁使用了超时进行重试
        try {
//            mutex.lockTimeOut(DEFAULT_TIMEOUT_PERIOD, TimeUnit.MICROSECONDS);// 阻塞等待值为true
            mutex.lock();
        } catch (Exception e) {
            e.printStackTrace();
            if (!mutex.state()) {
                lock();
            }
        }

        if (exception != null) {
            throw exception;
        }

        if (interrupt != null) {
            throw interrupt;
        }

        if (other != null) {
            throw new Exception("", other);
        }
    }

    /**
     * 尝试获取锁对象, 不会阻塞
     *
     * @throws InterruptedException
     * @throws KeeperException
     */
    public boolean tryLock() throws Exception {
        // 可能初始化的时候就失败了
        if (exception != null) {
            throw exception;
        }

        if (isOwner()) { // 锁重入
            return true;
        }

        acquireLock(null);

        if (exception != null) {
            throw exception;
        }

        if (interrupt != null) {
            Thread.currentThread().interrupt();
        }

        if (other != null) {
            throw new Exception("", other);
        }

        return isOwner();
    }

    /**
     * 释放锁对象
     */
    public void unlock() throws KeeperException {
        if (id != null) {
            try {
                System.out.println("释放锁 id=" + id);
                zookeeper.delete(root + "/" + id, -1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (KeeperException.NoNodeException e) {
                // do nothing
            } finally {
                id = null;
            }
        } else {
            //do nothing
        }
    }

    /**
     * 判断某path节点是否存在，不存在就创建
     *
     * @param path
     */
    private void ensureExists(final String path) {
        try {
            Stat stat = zookeeper.exists(path, false);
            if (stat != null) {
                return;
            }
            zookeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            exception = e;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            interrupt = e;
        }
    }

    /**
     * 返回锁对象对应的path
     */
    public String getRoot() {
        return root;
    }

    /**
     * 判断当前是不是锁的owner
     */
    public boolean isOwner() {
        return id != null && ownerId != null && id.equals(ownerId);
    }

    /**
     * 返回当前的节点id
     */
    public String getId() {
        return this.id;
    }

    // ===================== helper method =============================

    /**
     * 执行lock操作，允许传递watch变量控制是否需要阻塞lock操作
     */
    private Boolean acquireLock(final BooleanMutex mutex) {
        try {
            do {

                if (id == null) { // 构建当前lock的唯一标识
                    long sessionId = zookeeper.getSessionId();
                    String prefix = "x-" + sessionId + "-";

                    String path = zookeeper.create(root + "/" + prefix, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);// 如果第一次，则创建一个节点
                    int index = path.lastIndexOf("/");
                    id = StringUtils.substring(path, index + 1);
                    idName = id;  //TODO 可以删除 替换id
                }

                if (id != null) {

                    List<String> names = zookeeper.getChildren(root, false);
                    if (names.isEmpty()) {
                        id = null; // 异常情况，重新创建一个
                    } else {
                        // 对节点进行排序
                        SortedSet<String> sortedNames = new TreeSet();
                        for (String name : names) {
                            sortedNames.add(name);
                        }

                        if (!sortedNames.contains(idName)) {   //TODO 可以删除 替换id
                            id = null;// 清空为null，重新创建一个
                            continue;
                        }

                        ownerId = sortedNames.first();  // 将第一个节点做为ownerId
                        if (mutex != null && isOwner()) {
                            mutex.unlock();// 直接更新状态，返回
                            System.out.println("获取锁成功 id=" + id + "--------------------------ownerID--------------=" + ownerId);
                            return true;
                        } else if (mutex == null) {
                            System.out.println("获取锁成功 id=" + id + "--------------------------ownerID--------------=" + ownerId);
                            return isOwner();
                        }

                        SortedSet<String> lessThanMe = sortedNames.headSet(idName);   //TODO 可以删除 替换id
                        if (!lessThanMe.isEmpty()) {
                            // 关注一下排队在自己之前的最近的一个节点
                            String lastChildName = lessThanMe.last();
                            lastChildId = lastChildName;
                            // 异步watcher处理
                            System.out.println("获取锁失败 添加watch id=" + id + "--------------------------ownerID--------------=" + ownerId + "-----------------lastChildId=" + lastChildId);
                            Stat stat = zookeeper.exists(root + "/" + lastChildId, new Watcher() {
                                public void process(WatchedEvent event) {
                                    System.out.println("收到监听 id=" + id + "--------------------------ownerID--------------=" + ownerId + "-----------------lastChildId=" + lastChildId);
                                    acquireLock(mutex);
                                }
                            });

                            if (stat == null) {
                                acquireLock(mutex);// 如果节点不存在，需要自己重新触发一下，watcher不会被挂上去
                            }
                        } else {
                            if (isOwner()) {
                                mutex.unlock();
                            } else {
                                id = null;// 可能自己的节点已超时挂了，所以id和ownerId不相同
                            }
                        }
                    }
                }
            } while (id == null);
        } catch (KeeperException e) {
            exception = e;
            if (mutex != null) {
                mutex.unlock();
            }
        } catch (InterruptedException e) {
            interrupt = e;
            if (mutex != null) {
                mutex.unlock();
            }
        } catch (Throwable e) {
            other = e;
            if (mutex != null) {
                mutex.unlock();
            }
        }

        if (isOwner() && mutex != null) {
            mutex.unlock();
        }
        return Boolean.FALSE;
    }
}
