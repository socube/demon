package com.collection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/8.
 */
public class SetDemon extends TestCase{

    //底层hashmap 实现
    private HashSet<String> hashSet = new HashSet<String>();


    public void testSet(){
        hashSet.add("v");
        hashSet.add("f");
        hashSet.add("a");

        Iterator<String> iterator = hashSet.iterator();

        while (iterator.hasNext()){
            String next = iterator.next();

            System.out.println(next);
        }

    }



    public static void   demon(){

        JSONObject j_obj = new JSONObject();

        j_obj.put("name","34er");
        j_obj.put("age",23);
        j_obj.put("gla",454);

        TreeSet set = new TreeSet(j_obj.keySet());
        StringBuilder sb = new StringBuilder();
        Iterator it = set.iterator();

        String token = null;
        while(it.hasNext()) {
            token = (String)it.next();
            sb.append(j_obj.get(token));
            sb.append("_");
        }
        System.out.print(sb.toString());
    }


    public void testSort(){
        Set sortSet = new TreeSet<Object>();

    }
}
