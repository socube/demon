package freemark;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/11/21.
 */
public class FreeMarkerTest {

    private Configuration cfg = null;

    public static void main(String[] args) throws Exception {
        FreeMarkerTest maker = new FreeMarkerTest();
        maker.init();
        maker.process();
    }

    //初始化工作
    public void init() throws Exception {
        cfg = new Configuration();
        //设置模板文件位置
        Resource path = new DefaultResourceLoader().getResource("/template");
        File file = path.getFile();
        cfg.setDirectoryForTemplateLoading(file);//  /Users/wangxuedong/IdeaProjects/github/demon/web/src/main/resources
    }

    //模板 + 数据模型 = 输出
    public void process() throws Exception {
        //创建数据模型
        Map<String, String> rootMap = new HashMap<String, String>();
        rootMap.put("name", "FreeMarker");
        rootMap.put("message", "FreeMarker For Java Project");

        //使用Configuration实例加载指定模板
        Template template = cfg.getTemplate("user.ftl");
        //合并处理（模板 + 数据模型）
        //template.process(rootMap, new OutputStreamWriter(System.out));


        StringWriter writer = new StringWriter();

        template.process(rootMap, writer);

        String jsonStr = writer.toString();

        System.out.println("----------->"+jsonStr);

    }
}
