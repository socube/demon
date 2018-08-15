package com.proxy;

import clojure.main;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/8/21.
 */
public class Vendors implements Sell {
    @Override
    public void sell() {
        System.out.println("Vendors In sell method");
    }

    @Override
    public void ad() {
        System.out.println("Vendors In ad method");
    }

}
