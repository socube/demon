package spring;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.schema.People;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/7/6.
 */
public class SpringTest extends TestCase {


    public void testSchema() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        People p = (People) ctx.getBean("cutesource");
        System.out.println(p.getId());
        System.out.println(p.getName());
        System.out.println(p.getAge());
    }
}
