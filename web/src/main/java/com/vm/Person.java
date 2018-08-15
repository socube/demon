package com.vm;

import java.io.Serializable;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/21.
 */
public class Person implements Serializable {

    static final long serialVersionUID = 1L;

    String name; // 姓名

    Person friend;    //朋友

    public Person() {
    }

    public Person(String name) {
        super();
        this.name = name;
    }
}
