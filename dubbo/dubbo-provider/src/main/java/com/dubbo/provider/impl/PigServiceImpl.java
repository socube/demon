package com.dubbo.provider.impl;

import com.dubbo.api.AnimalService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/11.
 */
@Service("pigService")
public class PigServiceImpl implements AnimalService {


    public Integer getAge() {
        return 20;
    }

    public String sayHello(String name) {
        return "pig"+name+"hello!";
    }
}
