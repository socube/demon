package com.dubbo.springboot.common;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/16.
 */
public class User implements Serializable {


    private Long id;
    private String nick;
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
