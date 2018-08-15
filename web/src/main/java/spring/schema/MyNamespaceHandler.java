package spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/7/6.
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("people", new PeopleBeanDefinitionParser());
    }
}
