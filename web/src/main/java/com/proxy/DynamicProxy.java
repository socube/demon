package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description 动态代理的优势在于可以很方便的对代理类的函数进行统一的处理，而不用修改每个代理类的函数
 * @Author xuedong.wang
 * @Date 17/8/21.
 */
public class DynamicProxy implements InvocationHandler {

    private Object obj; //obj为委托类对象；

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(obj, args);
        System.out.println("after");
        return result;
    }
}
