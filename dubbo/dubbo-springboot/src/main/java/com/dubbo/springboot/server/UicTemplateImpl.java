package com.dubbo.springboot.server;


import com.dubbo.springboot.common.UicTemplate;
import com.dubbo.springboot.common.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/16.
 */
@Component
@DubboService(interfaceClass = UicTemplate.class)
public class UicTemplateImpl implements UicTemplate{

    public User findById(Long id) {
        User user = new User();
        user.setId(id);
        user.setNick("nick:" + id);
        user.setCreatedAt(new Date());
        return user;
    }

    public Optional<Long> isEmailUnique(String email) {
        return Optional.of(1L);
    }
}
