package com.spi.impl;

import com.spi.Spi;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/3/29.
 */
public class SpiBImpl implements Spi {


    public Boolean isSupport(String name) {
        return "SPIB".equalsIgnoreCase(name.trim());
    }

    public String sayHello() {
        return "hello 我是厂商B";
    }
}
