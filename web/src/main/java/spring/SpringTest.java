package spring;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.bean.SimpleBean;
import spring.schema.People;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/7/6.
 */
public class SpringTest extends TestCase {


    //自定义标签的使用
    public void testSchema() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        People p = (People) ctx.getBean("cutesource");
        System.out.println(p.getId());
        System.out.println(p.getName());
        System.out.println(p.getAge());
    }


    public void testBean() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //SimpleBean bean = context.getBean(SimpleBean.class);
        SimpleBean bean = (SimpleBean) context.getBean("simpleBean");
        bean.send();
        context.close();
    }


}
