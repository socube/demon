package com.api;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/5/10.
 */
public class Student implements Person {
    public String getNameAndAge(String name) {
        return "stu" + name + "age:10";
    }
}
