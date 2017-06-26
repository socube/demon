package com.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;

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


    private static String getSign(String params){
        StringBuilder sb = new StringBuilder();
        //MD5Util.getMD5Str(sb.toString()).toLowerCase();
        return null;
    }
}
