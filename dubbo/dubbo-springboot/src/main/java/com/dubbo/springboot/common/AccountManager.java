package com.dubbo.springboot.common;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/16.
 */
public interface AccountManager {


    public User findById(Long id);

    public void create(User user);
}
