package com.dubbo.provider.impl;

import com.dubbo.api.UserService;
import com.dubbo.api.entity.User;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/8.
 */
@Service("userService")
public class UserServiceImpl implements UserService {


    public String getUserNameById(Integer id) {
        return "wanguxedong";
    }

    public Integer getUserAgeById(Integer id) {
        return 22;
    }

    public User getUserById(Integer id) {

        User user = new User();

        user.setAge(23);
        user.setName("wangxuedong");
        return user;
    }

    public List<User> getUsers() {

        List<User> users = new ArrayList<User>();
        User user = new User();
        user.setAge(34);
        user.setName("xiaoxiao");

        User user1 = new User();
        user1.setAge(54);
        user1.setName("zhangzhang");


        User user2 = new User();
        user2.setAge(65);
        user2.setName("lili");

        users.add(user);
        users.add(user1);
        users.add(user2);

        return users;
    }
}
