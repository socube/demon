package com.dubbo.springboot.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/16.
 */
@SpringBootApplication
public class SpringBootDubboClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDubboClientApplication.class, args);
    }
}
