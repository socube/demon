package com.collection;

import junit.framework.TestCase;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by xd.wang on 16/11/14.
 */
public class QueueDemon extends TestCase {


    /**
     * 基于链表实现的一个阻塞队列，在创建LinkedBlockingQueue对象时如果不指定容量大小，则默认大小为Integer.MAX_VALUE。
     * 以上2种队列都是先进先出队列，而PriorityBlockingQueue却不是，它会按照元素的优先级对元素进行排序，按照优先级顺序出队，
     * 每次出队的元素都是优先级最高的元素。注意，此阻塞队列为无界阻塞队列，即容量没有上限（通过源码就可以知道，它没有容器满的信号标志）
     */
    private LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue();


    /**
     * 基于数组实现的阻塞队列，在创建ArrayBlockingQueue对象时必须指定其容量大小，还可以指定访问策略，默认情况下为非公平的，即不保证等待时间最长的线程最优先能够访问队列
     */
    private ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue(2);

    /**
     * 基于PriorityQueue，一种延时阻塞队列，DelayQueue中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素。DelayQueue也是一个无界队列，
     * 因此往队列中插入数据的操作（生产者）永远不会被阻塞，而只有获取数据的操作（消费者）才会被阻塞
     */
    private DelayQueue delayQueue = new DelayQueue();


    /**
     * put方法用来向队尾存入元素，如果队列满，则等待；
     * take方法用来从队首取元素，如果队列为空，则等待；
     * offer方法用来向队尾存入元素，如果队列满，则等待一定的时间，当时间期限达到时，如果还没有插入成功，则返回false；否则返回true；
     * poll方法用来从队首取元素，如果队列空，则等待一定的时间，当时间期限达到时，如果取到，则返回null；否则返回取得的元素；
     */
    public void testQueue() throws InterruptedException {

        arrayBlockingQueue.put(1);
        arrayBlockingQueue.put(2);
        //arrayBlockingQueue.put(3);
        //boolean offer = arrayBlockingQueue.offer(3, 1, TimeUnit.SECONDS);

        //System.out.println("offer=" + offer + " size=" + arrayBlockingQueue.size());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.size());
        arrayBlockingQueue.put(3);
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.size());
    }
}
