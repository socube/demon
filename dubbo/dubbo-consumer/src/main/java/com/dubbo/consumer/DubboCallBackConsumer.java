package com.dubbo.consumer;

import com.dubbo.api.CallbackListener;
import com.dubbo.api.CallbackService;
import com.dubbo.api.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/11.
 */
public class DubboCallBackConsumer {


    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:dubbo-consumer.xml");
        context.start();

        CallbackService callbackService = (CallbackService) context.getBean("callbackService");

        callbackService.addListener("http://10.20.160.198/wiki/display/dubbo/foo.bar", new CallbackListener(){
            public void changed(String msg) {
                System.out.println("callback1:" + msg);
            }
        });

//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
//
//        context.start();
//
//        UserService userService = (UserService) context.getBean("userService"); // 获取远程服务代理
//        String name = userService.getUserNameById(12); // 执行远程方法
//
//        System.out.println(name);
//
//        Thread.sleep(Integer.MAX_VALUE);
    }
}
