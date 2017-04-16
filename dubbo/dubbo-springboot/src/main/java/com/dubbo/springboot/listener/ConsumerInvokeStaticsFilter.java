package com.dubbo.springboot.listener;



import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/16.
 */

@Activate(group = Constants.CONSUMER, order = -110000)
public class ConsumerInvokeStaticsFilter extends StaticsFilter{
    public static Map<String, AtomicLong> statics = new ConcurrentHashMap<String, AtomicLong>();

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        increase(invocation.getClass(), invocation.getMethodName());
        return invoker.invoke(invocation);
    }
}
