package com.nio;

import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.Selector;

/**
 * @Description NIO
 * <p>
 * JDK1.4开始引入了NIO类库，这里的NIO指的是Non-blcok IO，主要是使用Selector多路复用器来实现。Selector在Linux等主流操作系统上是通过epoll实现的。
 * <p>
 * NIO的实现流程，类似于select：
 * <p>
 * 创建ServerSocketChannel监听客户端连接并绑定监听端口，设置为非阻塞模式。
 * 创建Reactor线程，创建多路复用器(Selector)并启动线程。
 * 将ServerSocketChannel注册到Reactor线程的Selector上。监听accept事件。
 * Selector在线程run方法中无线循环轮询准备就绪的Key。
 * Selector监听到新的客户端接入，处理新的请求，完成tcp三次握手，建立物理连接。
 * 将新的客户端连接注册到Selector上，监听读操作。读取客户端发送的网络消息。
 * 客户端发送的数据就绪则读取客户端请求，进行处理。
 * @Author xuedong.wang
 * @Date 17/8/29.
 */
public class NioDemo {


    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

    }
}
