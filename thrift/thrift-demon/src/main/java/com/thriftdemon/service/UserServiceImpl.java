package com.thriftdemon.service;

import com.thriftdemon.api.User;
import com.thriftdemon.api.UserService;
import org.apache.thrift.TException;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/16.
 */
public class UserServiceImpl implements UserService.Iface{
    @Override
    public User findUser() throws TException {
        User user = new User();
        user.setAge(27);
        user.setName("csy");
        return user;
    }

    @Override
    public void saveUser(User user) throws TException {
        System.out.println("save user success");
    }
}
