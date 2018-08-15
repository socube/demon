package com.dubbo.springboot.server;


import com.dubbo.springboot.common.AccountManager;
import com.dubbo.springboot.common.User;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/16.
 */
public class AccountManagerImpl implements AccountManager {

    public User findById(Long id) {
        User user = new User();
        user.setId(id);
        user.setNick("account:" + id);
        return user;
    }

    public void create(User user) {
        //

    }

}
