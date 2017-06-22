package com.lock;

/**
 * @Description 在多线程环境中，死锁意味着两个或多个线程一直阻塞，等待其他线程释放锁
 * @Author xuedong.wang
 * @Date 17/6/22.
 */
public class DeadlockSample {
    private final Object obj1 = new Object();
    private final Object obj2 = new Object();

    public static void main(String[] args) {
        DeadlockSample test = new DeadlockSample();
        test.testDeadlock();
    }

    private void testDeadlock() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                calLock12();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                calLock21();
            }
        });
        t1.start();
        t2.start();

    }

    private void calLock12() {
        synchronized (obj1) {
            sleep();
            synchronized (obj2) {
                sleep();
            }
        }
    }

    private void calLock21() {
        synchronized (obj2) {
            sleep();
            synchronized (obj1) {
                sleep();
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
