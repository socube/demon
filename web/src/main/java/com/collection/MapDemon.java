package com.collection;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/8.
 */
public class MapDemon extends TestCase{



    public void testMapiterotor(){

        Map<String,Object> map = new HashMap<String,Object>();


        map.put("name","wang");
        map.put("age",12);

    }

    public void testSortTreeMap(){
        Map<String,Object> tmap =new  TreeMap<String,Object>();

        tmap.put("yuwen", "语文");
        tmap.put("english", "英语");
        tmap.put("shuxue", "数学");
        tmap.put("zhenzhi", "政治");
        tmap.put("lishi", "历史");
        tmap.put("dili", "地理");
        tmap.put("wu", "生物");
        tmap.put("hua", "化学");

        int count = 0;
        for (Object str : tmap.values()){
            count ++;
            System.out.println("第 "+count+"="+str);
        }
    }
}
