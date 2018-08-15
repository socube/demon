package com.dubbo.springboot.uic;


import com.alibaba.dubbo.config.spring.ReferenceBean;
import org.mvnsearch.spring.boot.dubbo.DubboAutoConfiguration;
import org.mvnsearch.spring.boot.dubbo.DubboBasedAutoConfiguration;
import org.mvnsearch.uic.AccountManager;
import org.mvnsearch.uic.UicTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/16.
 */
@Configuration
@EnableConfigurationProperties(UicProperties.class)
@AutoConfigureAfter(DubboAutoConfiguration.class)
public class UicDemoAutoconfiguration extends DubboBasedAutoConfiguration {
    @Autowired
    private UicProperties properties;

    @Bean
    public ReferenceBean<UicTemplate> uicTemplate() {
        return getConsumerBean(UicTemplate.class, properties.getVersion(), properties.getTimeout());
    }

    @Bean
    public ReferenceBean<AccountManager> accountManager() {
        return getConsumerBean(AccountManager.class, properties.getVersion(), properties.getTimeout());
    }

}
