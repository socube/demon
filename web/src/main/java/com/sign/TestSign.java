package com.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.UTF8Decoder;
import junit.framework.TestCase;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/14.
 */
public class TestSign extends TestCase {


    public void testSignDemon() {

        //publishid=1676549&session=70f638907a795f5e1c66434f5574e79e&username=gues/tt&version=7.0&tp=th ok&so=g*o
        JSONObject obj = new JSONObject();
        obj.put("publishid",1676549);
        obj.put("session","70f638907a795f5e1c66434f5574e79e");
        obj.put("username","gues/tt");
        obj.put("version","7.0");
        obj.put("tp","th ok");
        obj.put("so","g*o");
        String str = JSON.toJSONString(obj);



    }

    private static String getSign(String params) {

        StringBuilder sb = new StringBuilder();
        JSONObject object = JSONObject.parseObject(params);
        TreeSet set = new TreeSet(object.keySet());
        Iterator it = set.iterator();
        String token;
        while (it.hasNext()) {
            token = (String) it.next();
            sb.append(token + "=" + object.get(token));
            sb.append("&");
        }
        sb.append("ilikeuxin");
        String sign = MD5Util.getMD5Str(sb.toString()).toLowerCase();
        return new StringBuilder()
                .append(sign.charAt(20))
                .append(sign.charAt(17))
                .append(sign.charAt(0))
                .append(sign.charAt(6))
                .append(sign.charAt(1))
                .append(sign.charAt(5)).toString();
    }


    private static String getEncode(String uri) {
        try {
            return URLEncoder.encode(uri, "UTF-8")
                    // OAuth encodes some characters differently:
                    .replace(" ", "&");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
