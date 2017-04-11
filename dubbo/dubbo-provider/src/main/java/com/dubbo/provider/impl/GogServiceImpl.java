package com.dubbo.provider.impl;

import com.dubbo.api.AnimalService;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/11.
 */
public class GogServiceImpl implements AnimalService {


    public Integer getAge() {
        return 10;
    }

    public String sayHello(String name) {
        return "dog" + name + "hello";
    }
}
