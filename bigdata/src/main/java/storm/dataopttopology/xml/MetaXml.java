package storm.dataopttopology.xml;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import storm.dataopttopology.util.MacroDef;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/**
 * @Description 消费者Metaq接口的配置xml读取接口
 * @Author xuedong.wang
 * @Date 17/5/19.
 */
public class MetaXml {

    //xml路径
    private static String fd;
    //MetaBolt参数
    //!--MetaQ消息队列--
    public static String MetaTopic;
    //!--MetaQ服务地址--
    public static String MetaZkConnect;
    //!--MetaQ服务路径--
    public static String MetaZkRoot;


    @SuppressWarnings("static-access")
    public MetaXml(String str){
        this.fd = str;
    }

    @SuppressWarnings("static-access")
    public void read(){
        try {
            File file = new File(this.fd);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            NodeList nl = doc.getElementsByTagName(MacroDef.Parameter);

            Element e = (Element)nl.item(0);

            MetaTopic = e.getElementsByTagName(MacroDef.MetaTopic).item(0).getFirstChild().getNodeValue();
            MetaZkConnect = e.getElementsByTagName(MacroDef.MetaZkConnect).item(0).getFirstChild().getNodeValue();
            MetaZkRoot = e.getElementsByTagName(MacroDef.MetaZkRoot).item(0).getFirstChild().getNodeValue();


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
