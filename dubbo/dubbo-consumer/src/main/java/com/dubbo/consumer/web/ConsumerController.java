package com.dubbo.consumer.web;

import com.dubbo.api.AnimalService;
import com.dubbo.api.UserService;
import com.dubbo.api.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/8.
 */
@RequestMapping("/consumer")
@Controller
public class ConsumerController {

    @Resource
    private UserService userService;

    @Resource
    private AnimalService pigService;

    @RequestMapping("getUsers")
    public List<User> getUses() {

        List<User> users = userService.getUsers();

        for (User user : users) {
            System.out.println("name=" + user.getName() + "age=" + user.getAge());
        }
        return users;
    }
}
