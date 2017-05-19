package com.storm.dataopttopology.xml;


import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import com.storm.dataopttopology.util.MacroDef;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/**
 * @Description 数据落地Mysql接口的xml配置读取接口
 * @Author xuedong.wang
 * @Date 17/5/19.
 */
public class MysqlXml {

    // xml路径
    private String fd;
    // Mysql参数
    // mysql地址及端口
    public static String Host_port;
    // 数据库名
    public static String Database;
    // 数据库名
    public static String From;
    // 用户名
    public static String Username;
    // 密码
    public static String Password;

    public MysqlXml(String str) {
        this.fd = str;
    }

    public void read() {

        try {
            File file = new File(this.fd);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            NodeList nl = doc.getElementsByTagName(MacroDef.Parameter);

            Element e = (Element) nl.item(0);

            Host_port = e.getElementsByTagName(MacroDef.Host_port).item(0)
                    .getFirstChild().getNodeValue();
            Database = e.getElementsByTagName(MacroDef.Database).item(0)
                    .getFirstChild().getNodeValue();
            Username = e.getElementsByTagName(MacroDef.Username).item(0)
                    .getFirstChild().getNodeValue();
            Password = e.getElementsByTagName(MacroDef.Password).item(0)
                    .getFirstChild().getNodeValue();
            From = e.getElementsByTagName(MacroDef.From).item(0)
                    .getFirstChild().getNodeValue();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
