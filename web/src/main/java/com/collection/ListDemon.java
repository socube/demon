package com.collection;

import junit.framework.TestCase;

import java.util.*;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/6/8.
 */
public class ListDemon extends TestCase {

    //默认大小10
    private ArrayList<String>  arrayList = new ArrayList<String>();

    private Vector<String> vector = new Vector<String>();

    private LinkedList<String> linkedList = new LinkedList<String>();



    public void testList(){
        linkedList.add("erer");
        linkedList.add("vfvf");
        linkedList.add(0,"ree");

        System.out.println(linkedList.size());

        Collection list  = new ArrayList<String>();

//        Collections.copy();
    }
}
