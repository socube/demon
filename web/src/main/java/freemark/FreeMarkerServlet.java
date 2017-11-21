package freemark;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/11/21.
 */
public class FreeMarkerServlet extends HttpServlet {

    private Configuration cfg = null;

    public void init() throws ServletException {
        cfg = new Configuration();
        //设置模板文件位置
        cfg.setServletContextForTemplateLoading(getServletContext(), "/template");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        //建立数据模型
        Map<String, String> rootMap = new HashMap<String, String>();
        rootMap.put("name", "FreeMarker模板");
        rootMap.put("message", "FreeMarker For JavaWeb");

        try {
            //取得模板文件
            Template template = cfg.getTemplate("hello.ftl");
            response.setContentType("text/html; charset=utf-8 ");
            Writer out = response.getWriter();
            //合并数据模型和模板，并将结果输出到response.getWriter()中
            template.process(rootMap, out);
        }catch(IOException e) {
            e.printStackTrace();
        }catch(TemplateException t) {
            t.printStackTrace();
        }
    }
}
