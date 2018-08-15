package com.lock;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/8/17.
 */
public class LockTest extends TestCase {

    private ReentrantLock lock = new ReentrantLock();

    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();




    public void testReadWriteLock(){

        final LockTest test = new LockTest();

        new Thread(){
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();

        new Thread(){
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();

    }

    public void get(Thread thread) {
        reentrantReadWriteLock.readLock().lock();
        try {
            long start = System.currentTimeMillis();

            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"正在进行读操作");
            }
            System.out.println(thread.getName()+"读操作完毕");
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }


//    public synchronized void get(Thread thread) {
//        long start = System.currentTimeMillis();
//        while(System.currentTimeMillis() - start <= 1) {
//            System.out.println(thread.getName()+"正在进行读操作");
//        }
//        System.out.println(thread.getName()+"读操作完毕");
//    }



    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public static void main(String[] args)  {

        final LockTest test = new LockTest();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();
    }

    public void insert(Thread thread) {
        //Lock lock = new ReentrantLock();    //注意这个地方
        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            for(int i=0;i<5;i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }
    }
}
