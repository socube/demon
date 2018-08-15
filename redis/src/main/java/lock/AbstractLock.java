package lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Description  抽象
 * @Author xuedong.wang
 * @Date 17/1/6.
 */
public abstract class AbstractLock implements Lock {

    public static final Logger logger = LoggerFactory.getLogger(AbstractLock.class);
    /**
     * <pre>
     * 这里需不需要保证可见性值得讨论, 因为是分布式的锁,
     * 1.同一个jvm的多个线程使用不同的锁对象其实也是可以的, 这种情况下不需要保证可见性
     * 2.同一个jvm的多个线程使用同一个锁对象, 那可见性就必须要保证了.
     * </pre>
     */
    protected volatile boolean locked;

    /**
     * 当前jvm内持有该锁的线程(if have one) TODO 分布式没有用
     */
    private Thread exclusiveOwnerThread;

    public void lock() {
        try {
            lock(false, 0, null, false);
        } catch (InterruptedException e) {
            // TODO ignore
        }
    }

    public void lockInterruptibly() throws InterruptedException {
        lock(false, 0, null, true);
    }

    public boolean tryLock(long time, TimeUnit unit) {
        try {
            return lock(true, time, unit, false);
        } catch (InterruptedException e) {
            // TODO ignore
        }
        return false;
    }

    public boolean tryLockInterruptibly(long time, TimeUnit unit) throws InterruptedException {
        return lock(true, time, unit, true);
    }

    public void unlock() {

        unlock0();
//        if (Thread.currentThread() != getExclusiveOwnerThread()) {
//            //throw new IllegalMonitorStateException("current thread does not hold the lock");
//            logger.error("current thread does not hold the lock");
//        } else {
//            System.out.print("unlock SUCCESS");
//            unlock0();
//            setExclusiveOwnerThread(null);
//        }
    }

    protected void setExclusiveOwnerThread(Thread thread) {
        exclusiveOwnerThread = thread;
    }

    protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }

    protected abstract void unlock0();

    /**
     * 阻塞式获取锁的实现
     *
     * @param useTimeout
     * @param time
     * @param unit
     * @param interrupt  是否响应中断
     * @return
     * @throws InterruptedException
     */
    protected abstract boolean lock(boolean useTimeout, long time, TimeUnit unit, boolean interrupt) throws InterruptedException;
}
