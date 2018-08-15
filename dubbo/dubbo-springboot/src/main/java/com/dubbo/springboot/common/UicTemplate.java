package com.dubbo.springboot.common;

import java.util.Optional;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/16.
 */
public interface UicTemplate {


    public User findById(Long id);

    Optional<Long> isEmailUnique(String email);
}
