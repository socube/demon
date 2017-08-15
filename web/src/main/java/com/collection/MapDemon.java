package com.collection;

import junit.framework.TestCase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/8.
 */
public class MapDemon extends TestCase {








    public void testCurrentHashMap(){
        ConcurrentHashMap map = new ConcurrentHashMap();
    }

    public void testMutableKey() {
        class MyKey {
            Integer i;

            public void setI(Integer i) {
                this.i = i;
            }

            public MyKey(Integer i) {
                this.i = i;
            }

            @Override
            public int hashCode() {
                // 如果返回1
                // return 1
                return i;
            }

            // object作为key存map里，必须实现equals方法
            @Override
            public boolean equals(Object obj) {
                if (obj instanceof MyKey) {
                    return i.equals(((MyKey) obj).i);
                } else {
                    return false;
                }
            }
        }

        // 我机器配置不高，25000的话正常情况27毫秒，可以用2500万试试，如果hashCode()方法返回1的话，250万就卡死
        Map<MyKey, String> map = new HashMap<MyKey, String>();
        Date begin = new Date();
        for (int i = 0; i < 200000; i++) {
            map.put(new MyKey(i), "test " + i);
        }

        Date end = new Date();
        System.out.println("时间(ms) " + (end.getTime() - begin.getTime()));
    }


    public void testSys() {
        System.out.println(1 << 30);
    }

    public void testMapiterotor() {

        Map<String, Object> map = new HashMap<String, Object>();


        map.put("name", "wang");
        map.put("age", 12);

    }

    public void testSortTreeMap() {
        Map<String, Object> tmap = new TreeMap<String, Object>();

        tmap.put("yuwen", "语文");
        tmap.put("english", "英语");
        tmap.put("shuxue", "数学");
        tmap.put("zhenzhi", "政治");
        tmap.put("lishi", "历史");
        tmap.put("dili", "地理");
        tmap.put("wu", "生物");
        tmap.put("hua", "化学");

        int count = 0;
        for (Object str : tmap.values()) {
            count++;
            System.out.println("第 " + count + "=" + str);
        }
    }
}
