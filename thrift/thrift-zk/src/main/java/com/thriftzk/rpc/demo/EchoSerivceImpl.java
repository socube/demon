package com.thriftzk.rpc.demo;

import org.apache.thrift.TException;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/15.
 */
public class EchoSerivceImpl implements EchoSerivce.Iface {
    @Override
    public String echo(String msg) throws TException {
        return "server :"+msg;
    }
}
