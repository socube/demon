package com;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/8/21.
 */
public class Test2 {

    static {

        System.out.println("静态初始化块执行了！");

    }

    public static void main(String[] args) {
        int a = 0;

        System.out.println("返回值"+test(1));
    }

    public static int test(int a){
        try {
            return a + 1;
        }finally {
           System.out.println(a=a+2);
        }


    }


}
