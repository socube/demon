package com.thriftdemon.service;

import com.thriftdemon.api.HelloService;
import org.apache.thrift.TException;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/16.
 */
public class HelloServiceImpl implements HelloService.Iface{
    public String hello(String name) throws TException {
        return "hello " + name;
    }
}
