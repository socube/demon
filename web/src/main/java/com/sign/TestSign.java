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

        JSONObject obj = new JSONObject();
        obj.put("name","wangxuedong");
        obj.put("sex",1);
        obj.put("age",20);

        String str = JSON.toJSONString(obj);



    }


    private static String getSign(String params){

       // MD5Util.getMD5Str(sb.toString()).toLowerCase();
        return null;
    }
}
