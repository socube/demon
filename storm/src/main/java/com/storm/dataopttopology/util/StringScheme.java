package com.storm.dataopttopology.util;


import org.apache.storm.spout.Scheme;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.List;
/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/5/19.
 */
public class StringScheme implements Scheme {

//    public List<Object> deserialize(byte[] bytes) {
//
//        try {
//
//            //数据的编码定义
//            return new Values(new String(bytes, MacroDef.ENCODING));
//
//        } catch (UnsupportedEncodingException e) {
//
//            throw new RuntimeException(e);
//
//        }
//    }

    @Override
    public List<Object> deserialize(ByteBuffer byteBuffer) {
        try {
            return new Values(new String(byteBuffer.array(), MacroDef.ENCODING));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Fields getOutputFields() {
        return new Fields("str");
    }
}
