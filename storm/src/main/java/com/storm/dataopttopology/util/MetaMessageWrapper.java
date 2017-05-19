package com.storm.dataopttopology.util;

import java.util.concurrent.CountDownLatch;
import com.taobao.metamorphosis.Message;
/**
 * @Description Meta消息封装
 * @Author xuedong.wang
 * @Date 17/5/19.
 */
public class MetaMessageWrapper {


    public final Message message;
    public final CountDownLatch latch;
    public volatile boolean success = false;

    public MetaMessageWrapper(final Message message) {
        super();
        this.message = message;
        this.latch = new CountDownLatch(1);
    }
}
