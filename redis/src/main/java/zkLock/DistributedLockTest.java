package zkLock;

import org.apache.zookeeper.KeeperException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description 测试类
 * @Author xuedong.wang
 * @Date 17/1/8.
 */
public class DistributedLockTest {


    public static void main(String [] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        final int count = 50;
        final CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            final ZkDistributedLock node = new ZkDistributedLock("/locks");
            executor.submit(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                        node.tryLock(); // 无阻塞获取锁
                        //node.lock(); // 阻塞获取锁
                        Thread.sleep(100);

                        System.out.println("ID: " + node.getId() + " GET LOCK: " + node.isOwner());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                        try {
                            //System.out.println("ID: " + node.getId() + " GET LOCK: " + node.isOwner());
                            node.unlock();
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
