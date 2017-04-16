package com.dubbo.springboot.listener;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/16.
 */
public class ProviderInvokeStaticsFilter extends StaticsFilter {


    @SuppressWarnings("Duplicates")
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        increase(invoker.getInterface(), invocation.getMethodName());
        return invoker.invoke(invocation);
    }
}
