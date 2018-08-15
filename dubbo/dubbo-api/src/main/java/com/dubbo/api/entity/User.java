package com.dubbo.api.entity;

import java.io.Serializable;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/8.
 */
public class User implements Serializable{

    private Integer age;

    private String name;


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
