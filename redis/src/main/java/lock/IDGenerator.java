package lock;

import java.math.BigInteger;

/**
 * @Description  测试
 * @Author xuedong.wang
 * @Date 17/1/6.
 */
public class IDGenerator implements Releasable {


    private static BigInteger id = BigInteger.valueOf(0);

    private final Lock lock;

    private static final BigInteger INCREMENT = BigInteger.valueOf(1);

    public IDGenerator(Lock lock) {
        this.lock = lock;
    }

    public String getAndIncrement() throws InterruptedException {
        if (lock.tryLock()) {
            try {
                // TODO 这里获取到锁, 访问临界区资源
                System.out.println(Thread.currentThread().getName() + " get lock");
                return getAndIncrement0();
            } finally {
                System.out.println(Thread.currentThread().getName() + "  unlock");
                lock.unlock();
            }
        }
        return null;
        //return getAndIncrement0();
    }

    public void release() {
        lock.release();
    }

    private String getAndIncrement0() throws InterruptedException {

        Thread.sleep(8000);
        String s = id.toString();
        id = id.add(INCREMENT);
        return s;
    }
}
