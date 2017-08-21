package com;

import junit.framework.TestCase;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/8/21.
 */
public class ClassLoaderTest extends TestCase{




    public void testLoader(){
    }

    public void testClassloader(){

        java.lang.ClassLoader loader = Thread.currentThread().getContextClassLoader();

        System.out.println(loader);

        System.out.println(loader.getParent());

        System.out.println(loader.getParent().getParent());
    }
}
