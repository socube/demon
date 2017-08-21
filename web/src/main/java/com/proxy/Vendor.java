package com.proxy;

/**
 * @Description 委托类
 * @Author xuedong.wang
 * @Date 17/8/21.
 */
public class Vendor implements Sell{

    public void sell() {
        System.out.println("In sell method");
    }
    public void ad() {
        System.out.println("ad method");
    }
}
