package com.dubbo.consumer;

import com.dubbo.api.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/8.
 */
public class DubboConsumer {

    public static void main(String[] args) throws InterruptedException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");

        context.start();

        UserService userService = (UserService) context.getBean("userService"); // 获取远程服务代理
        String name = userService.getUserNameById(12); // 执行远程方法

        System.out.println(name);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
