package com.proxy;

/**
 * @Description  静态代理的局限在于运行前必须编写好代理类
 * @Author xuedong.wang
 * @Date 17/8/21.
 */
public class BusinessAgent implements Sell {

    private Vendor mVendor;

    public BusinessAgent(Vendor vendor) {
        mVendor = vendor;
    }

    public void sell() {
        System.out.println("before");
        mVendor.sell();
        System.out.println("after");
    }

    public void ad() {
        System.out.println("before");
        mVendor.ad();
        System.out.println("after");
    }
}
