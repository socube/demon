package com.thriftzk.rpc;

/**
 * @Description 自定义异常
 * @Author xuedong.wang
 * @Date 17/6/15.
 */
public class ThriftException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ThriftException() {
        super();
    }

    public ThriftException(String msg) {
        super(msg);
    }

    public ThriftException(Throwable e) {
        super(e);
    }

    public ThriftException(String msg, Throwable e) {
        super(msg, e);
    }
}
