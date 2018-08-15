package com.collection;

import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;

import java.util.*;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/8.
 */
public class SetDemon extends TestCase {

    //底层hashmap 实现 不同步
    private HashSet<String> hashSet = new HashSet<String>();
    //继承hashset 双重链接列表 不同步
    private LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>();
    //treeMap 二叉树实现 排序
    private TreeSet<String> treeSet = new TreeSet<String>();

    public void testSet() {
        treeSet.add("v");
        treeSet.add("f");
        treeSet.add("a");

        Iterator<String> iterator = treeSet.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();

            System.out.println(next);
        }

        synchronized (this){

        }
    }


    public static void demon() {

        JSONObject j_obj = new JSONObject();

        j_obj.put("name", "34er");
        j_obj.put("age", 23);
        j_obj.put("gla", 454);

        TreeSet set = new TreeSet(j_obj.keySet());
        StringBuilder sb = new StringBuilder();
        Iterator it = set.iterator();

        String token = null;
        while (it.hasNext()) {
            token = (String) it.next();
            sb.append(j_obj.get(token));
            sb.append("_");
        }
        System.out.print(sb.toString());
    }


    public void testSort() {
        Set sortSet = new TreeSet<Object>();

    }
}
