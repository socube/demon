package com.thread;

import junit.framework.TestCase;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/8/14.
 */
public class ThreadTest extends TestCase{


    public void testOne() throws InterruptedException {

        Thread thread = new Thread(new ThreadT());
        thread.start();

        //Thread.sleep(500);
        System.out.println("end");


        System.out.println( Thread.currentThread().getThreadGroup().getName());

    }




    public class ThreadT implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
