package com.thread;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/9.
 */
public class ThreadPoolTest extends TestCase {

    private static final int COUNT = 10000;

    public void testThreadPool() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(COUNT);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        long bg = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            Runnable command = new TestRunnable(countDownLatch);
            executorService.execute(command);
        }
        countDownLatch.await();
        System.out.println("testThreadPool:" + (System.currentTimeMillis() - bg));
    }

    public void testNewThread() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(COUNT);
        long bg = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            Runnable command = new TestRunnable(countDownLatch);
            Thread thread = new Thread(command);
            thread.start();
        }
        countDownLatch.await();
        System.out.println("testNewThread:" + (System.currentTimeMillis() - bg));
    }

    private static class TestRunnable implements Runnable {
        private final CountDownLatch countDownLatch;

        TestRunnable(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            countDownLatch.countDown();
        }
    }
}
