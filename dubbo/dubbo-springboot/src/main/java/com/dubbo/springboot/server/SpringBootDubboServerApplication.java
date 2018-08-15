package com.dubbo.springboot.server;



import com.dubbo.springboot.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/16.
 */
@SpringBootApplication
@EnableDubboConfiguration
public class SpringBootDubboServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDubboServerApplication.class, args);
    }
}
