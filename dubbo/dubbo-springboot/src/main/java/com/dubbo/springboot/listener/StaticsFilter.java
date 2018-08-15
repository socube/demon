package com.dubbo.springboot.listener;
import com.alibaba.dubbo.rpc.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/16.
 */
public class StaticsFilter  implements Filter{

    public static Map<String, AtomicLong> statics = new ConcurrentHashMap<String, AtomicLong>();

    public static void increase(Class clazz, String methodName) {
        String key = clazz.getCanonicalName() + "." + methodName;
        if (!statics.containsKey(key)) {
            statics.put(key, new AtomicLong(0));
        }
        statics.get(key).incrementAndGet();
    }

    public static long getValue(Class clazz, String methodName) {
        String key = clazz.getCanonicalName() + "." + methodName;
        AtomicLong value = statics.get(key);
        return value != null ? value.get() : 0;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return null;
    }
}
