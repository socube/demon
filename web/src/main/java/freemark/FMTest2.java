package freemark;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.TemplateException;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/11/21.
 */
public class FMTest2 {
    public static void main(String[] args) {

        FreeMarkerUtils2 utl = new FreeMarkerUtils2();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("size", 0);
        map.put("gte_val", "1497283200000");
        map.put("lte_val", "1497928996980");
        map.put("min_val", "1497283200000");
        map.put("max_val", "1497928996980");
        map.put("interval", "21526566ms");

        try {
            //获取工程路径
            String url = System.getProperty("user.dir");
            String content =  utl.getContent("/files/ftl","eventflow.ftl",map);
            //将数据写入eventflow.json文件
            utl.printFile("/files/ftl","eventflow.ftl", map, new File(url + "/src/main/sources/files/json/eventflow.json"));
            System.out.println("返回的json" + "\n" + content + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
