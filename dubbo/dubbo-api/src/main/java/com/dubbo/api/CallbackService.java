package com.dubbo.api;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/11.
 */
public interface CallbackService {

    void addListener(String key, CallbackListener listener);
}
