package com.lock;

import com.sun.javafx.scene.control.skin.TableCellSkin;
import junit.framework.TestCase;

/**
 * @Description 在多线程环境中，死锁意味着两个或多个线程一直阻塞，等待其他线程释放锁
 * @Author xuedong.wang
 * @Date 17/6/22.
 */
public class DeadlockSample extends TestCase {





    private volatile Integer i = 0;

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


    /**
     *Java中的引用类型

     java.lang.ref包能用来声明软引用（soft reference），弱引用（weak reference）和虚引用（phantom reference）。

     垃圾收集器不会回收强引用。
     在内存不足时才会回收软引用，所以用它实现缓存可以避免内存不足。
     垃圾收集器将会在下一次垃圾收集时回收弱引用。弱引用能被用来实现特殊的map。java.util.WeakHashMap中的key就是弱引用。
     虚引用会被立即回收。能被用来跟踪对象被垃圾回收的活动。
     */
}
