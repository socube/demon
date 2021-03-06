package com.dubbo.springboot;


import com.alibaba.dubbo.config.spring.ReferenceBean;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/16.
 */
public class DubboConsumerBuilder {

    private Class interfaceClazz;
    private String version;
    private Integer timeout;

    public static DubboConsumerBuilder create() {
        return new DubboConsumerBuilder();
    }

    public DubboConsumerBuilder service(Class interfaceClazz) {
        this.interfaceClazz = interfaceClazz;
        return this;
    }

    public DubboConsumerBuilder version(String version) {
        this.version = version;
        return this;
    }

    public DubboConsumerBuilder timeout(Integer timeout) {
        this.timeout = timeout;
        return this;
    }

    public ReferenceBean build() {
        ReferenceBean consumerBean = new ReferenceBean();
        String canonicalName = interfaceClazz.getCanonicalName();
        consumerBean.setInterface(canonicalName);
        consumerBean.setId(canonicalName);
        consumerBean.setVersion(version);
        consumerBean.setTimeout(timeout);
        return consumerBean;
    }
}
