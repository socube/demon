package lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Description 实现分布式锁
 * @Author xuedong.wang
 * @Date 17/1/6.
 */
public class RedisBasedDistributedLock extends AbstractLock {


    public static final Logger logger = LoggerFactory.getLogger(RedisBasedDistributedLock.class);

    //private RedisManager redisManager;

    // 锁的名字
    protected String lockKey;

    // 锁的有效时长(毫秒)
    protected long lockExpires;

    //锁的时间戳
    private String stringOfLockExpireTime;


//    public RedisBasedDistributedLock(RedisManager redisManager, String lockKey, long lockExpires) throws IOException {
//        this.redisManager = redisManager;
//        this.lockKey = lockKey;
//        this.lockExpires = lockExpires;
//    }

    // 阻塞式获取锁的实现
    protected boolean lock(boolean useTimeout, long time, TimeUnit unit, boolean interrupt) throws InterruptedException {

        if (interrupt) {
            checkInterruption();
        }

        // 超时控制 的时间可以从本地获取, 因为这个和锁超时没有关系, 只是一段时间区间的控制
        long start = localTimeMillis();
        long timeout = unit.toMillis(time); // if !useTimeout, then it's useless

        while (useTimeout ? isTimeout(start, timeout) : true) {
            if (interrupt) {
                checkInterruption();
            }

            long lockExpireTime = localTimeMillis() + lockExpires + 1;//锁超时时间
            stringOfLockExpireTime = String.valueOf(lockExpireTime);

//            if (redisManager.setnx(lockKey, stringOfLockExpireTime) == 1) { // 获取到锁
//                logger.info("lock成功获取锁 lockKey:{},失效时间:{},阻塞时间:{}", lockKey, lockExpires, time);
//                locked = true;
//                setExclusiveOwnerThread(Thread.currentThread());
//                return true;
//            }

            //String value = redisManager.get(lockKey);
//            if (value != null && isTimeExpired(value)) { // lock is expired
//
//                logger.info("锁超时 lockKey:{},失效时间:{},阻塞时间:{}", lockKey, lockExpires, time);
//                String oldValue = redisManager.getSet(lockKey, stringOfLockExpireTime); //假设多个线程(非单jvm)同时走到这里  getset is atomic
//                /**
//                 * 但是走到这里时每个线程拿到的oldValue肯定不可能一样(因为getset是原子性的)
//                 加入拿到的oldValue依然是expired的，那么就说明拿到锁了
//
//                 * */
//                if (oldValue != null && isTimeExpired(oldValue)) {
//                    // TODO 成功获取到锁, 设置相关标识
//                    locked = true;
//                    setExclusiveOwnerThread(Thread.currentThread());
//                    return true;
//                }
//            } else {
//                // TODO lock is not expired, enter next loop retrying
//            }
        }
        return false;
    }

    public boolean tryLock() {

        long lockExpireTime = localTimeMillis() + lockExpires + 1;//锁超时时间

        stringOfLockExpireTime = String.valueOf(lockExpireTime);

//        if (redisManager.setnx(lockKey, stringOfLockExpireTime) == 1) { // 获取到锁
//
//            logger.info("tryLock获取成功锁 lockKey:{}", lockKey);
//            // TODO 成功获取到锁, 设置相关标识
//            locked = true;
//            setExclusiveOwnerThread(Thread.currentThread());
//            return true;
//        }

//        String value = redisManager.get(lockKey);
//
//        if (value != null && isTimeExpired(value)) { // lock is expired
//
//            logger.info("锁超时 lockKey:{}", lockKey);
//
//            // 假设多个线程(非单jvm)同时走到这里
//            String oldValue = redisManager.getSet(lockKey, stringOfLockExpireTime); // getset is atomic
//            // 但是走到这里时每个线程拿到的oldValue肯定不可能一样(因为getset是原子性的)
//            // 假如拿到的oldValue依然是expired的，那么就说明拿到锁了
//            if (oldValue != null && isTimeExpired(oldValue)) {
//                logger.info("重新获取到锁 lockKey:{}", lockKey);
//                // TODO 成功获取到锁, 设置相关标识
//                locked = true;
//                setExclusiveOwnerThread(Thread.currentThread());  //TODO 没有意义 删除
//                return true;
//            }
//        } else {
//            // TODO lock is not expired, enter next loop retrying
//        }
        logger.info("获取锁失败 lockKey:{}", lockKey);
        return false;
    }

    /**
     * Queries if this lock is held by any thread.
     *
     * @return {@code true} if any thread holds this lock and
     * {@code false} otherwise
     */
    public boolean isLocked() {
        if (locked) {
            return true;
        } else {
           // String value = redisManager.get(lockKey);
            // TODO 这里其实是有问题的, 想:当get方法返回value后, 假设这个value已经是过期的了,
            // 而就在这瞬间, 另一个节点set了value, 这时锁是被别的线程(节点持有), 而接下来的判断
            // 是检测不出这种情况的.不过这个问题应该不会导致其它的问题出现, 因为这个方法的目的本来就
            // 不是同步控制, 它只是一种锁状态的报告.
            //return !isTimeExpired(value);
        }
        return false;
    }

    @Override
    protected void unlock0() {

       // String value = redisManager.get(lockKey); // TODO 判断锁是否过期

//        if (!isTimeExpired(value) && stringOfLockExpireTime.equals(value)) { //锁没有超时 释放锁必须持有锁
//            doUnlock();
//            logger.info("成功释放锁 lockKey:{}", lockKey);
//        } else {
//            logger.info("释放锁失败 当前线程没有持有锁 lockKey:{}", lockKey);
//        }
    }

    public void release() {
        //TODO  关闭连接
    }

    private void doUnlock() {
        //redisManager.del(lockKey);
    }

    private void checkInterruption() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
    }

    private boolean isTimeExpired(String value) {
        // 这里拿服务器的时间来比较
        return Long.parseLong(value) < localTimeMillis();
    }

    private boolean isTimeout(long start, long timeout) {
        // 这里拿本地的时间来比较
        return start + timeout > System.currentTimeMillis();
    }


    private long localTimeMillis() {
        return System.currentTimeMillis();
    }
}
