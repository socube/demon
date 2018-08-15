package com.dubbo.springboot;

import java.lang.annotation.*;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/4/16.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface EnableDubboConfiguration {
    /**
     * scan package for dubbo
     */
    String value() default "";
}
