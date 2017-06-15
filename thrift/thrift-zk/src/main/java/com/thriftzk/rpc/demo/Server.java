package com.thriftzk.rpc.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/15.
 */
public class Server {

    public static void main(String[] args) {
        try {
            new ClassPathXmlApplicationContext("classpath:spring-context-thrift-server.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
