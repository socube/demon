package com.dubbo.api;

import antlr.collections.impl.LList;
import com.dubbo.api.entity.User;

import java.util.List;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/8.
 */
public interface UserService {

    String getUserNameById(Integer id);

    Integer getUserAgeById(Integer id);

    User getUserById(Integer id);

    List<User> getUsers();

}
