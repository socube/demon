package com;

import com.spi.Spi;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xd.wang on 16/11/14.
 */
public class Test {


    public static void main(String[] args) {


        ServiceLoader.load(Spi.class);

    }
}
