package com.dubbo.springboot.uic;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/16.
 */
@ConfigurationProperties(prefix = "spring.uic")
public class UicProperties {
    /**
     * dubbo服务版本号,默认值为空
     */
    private String version = "";
    /**
     * rpc服务调用超时时间
     */
    private Integer timeout = 3000;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
