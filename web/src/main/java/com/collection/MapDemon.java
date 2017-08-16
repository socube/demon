package com.collection;

import junit.framework.TestCase;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/8.
 */
public class MapDemon extends TestCase {


    //键不可重复 值可重复 底层哈希表 线程不安全 允许key 为null value 也可以为null 无序  table16
    private HashMap<String, String> hashMap = new HashMap();
    //键不可重复 值可重复 底层哈希表 线程安全 key value 都不允许为null
    private Hashtable<String, String> hashtable = new Hashtable();
    ///键不可重复 值可重复 底层二叉树 (红黑树)
    private TreeMap<String, String> treeMap = new TreeMap<String, String>();
    //hashmap 子类 有序 双向链表
    private LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
    //WeakHashMap 是一种改进的 HashMap，它对 key 实行“弱引用”，如果一个 key 不再被外部所引用，那么该 key 可以被 GC 回收
    private WeakHashMap<String, String> weakHashMap = new WeakHashMap<String, String>();
    //线程安全 高效table
    private ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap();
    //key 可以重复 "＝＝" 判断
    private IdentityHashMap identityHashMap = new IdentityHashMap();

    private Properties properties = new Properties();

    public void testMap() {
        String put = hashMap.put("c", "c");
        String put1 = hashMap.put("c", "f");
        System.out.println("put=" + put + " put1=" + put1);
        hashMap.put("a", "a");

        Set<Map.Entry<String, String>> entries = hashMap.entrySet();

        for (Map.Entry entry : entries) {
            //System.out.println(entry.getKey());
        }

    }

    public void testCurrentHashMap() {
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
