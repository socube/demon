package com.thread;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/8/28.
 */
public class RubbishRelease {


    // 类的finalize方法，可以告诉垃圾回收器应该执行的操作，该方法从Object类继承而来。
    // 在从堆中永久删除对象之前，垃圾回收器调用该对象的finalize方法。
    public void finalize() {
        System.out.println("the Object is going...");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            // 下面不断创建对象，但是这些对象都没有被引用
            new RubbishRelease();
            new RubbishRelease();
            new RubbishRelease();
            System.gc();
        }
        System.out.println("The program is over!");
    }
}
