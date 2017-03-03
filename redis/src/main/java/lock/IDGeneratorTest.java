package lock;

import comyouxinpai.changzheng.commons.cache.redis.RedisManager;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description   测试
 * @Author xuedong.wang
 * @Date 17/1/6.
 */
public class IDGeneratorTest {


    private static Set<String> generatedIds = new HashSet<String>();

    private static final String LOCK_KEY = "lock.lock";
    private static final long LOCK_EXPIRE = 5 * 1000;

    public static void main(String[] args) throws IOException, InterruptedException {

        RedisManager redisManager =RedisManager.getInstance();

        Lock lock1 = new RedisBasedDistributedLock(redisManager, LOCK_KEY, LOCK_EXPIRE);
        IDGenerator g1 = new IDGenerator(lock1);
        IDConsumeTask consume1 = new IDConsumeTask(g1, "consume0");

        Lock lock2 = new RedisBasedDistributedLock(redisManager, LOCK_KEY, LOCK_EXPIRE);
        IDGenerator g2 = new IDGenerator(lock2);
        IDConsumeTask consume2 = new IDConsumeTask(g2, "consume1");

        Thread t1 = new Thread(consume1);
        Thread t2 = new Thread(consume2);
        t1.start();
        t2.start();

        Thread.sleep(20 * 1000); //让两个线程跑20秒

        IDConsumeTask.stopAll();

        t1.join();
        t2.join();
    }

    static String time() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    static class IDConsumeTask implements Runnable {

        private IDGenerator idGenerator;

        private String name;

        private static volatile boolean stop;

        public IDConsumeTask(IDGenerator idGenerator, String name) {
            this.idGenerator = idGenerator;
            this.name = name;
        }

        public static void stopAll() {
            stop = true;
        }

        public void run() {
            System.out.println(time() + ": consume " + name + " start ");
            while (!stop) {
                String id = null;
                try {
                    id = idGenerator.getAndIncrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (id != null) {
                    if (generatedIds.contains(id)) {
                        System.out.println(time() + ": duplicate id generated, id = " + id);
                        stop = true;
                        continue;
                    }

                    generatedIds.add(id);
                    System.out.println(time() + ": consume " + name + " add id = " + id);
                }
            }
            // 释放资源
            idGenerator.release();
            System.out.println(time() + ": consume " + name + " done ");
        }

    }
}
