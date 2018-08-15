package com.thriftdemon.tsimpleserver;


import com.thriftdemon.api.HelloService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @Description 客户端调用HelloTSimpleServer, HelloTThreadPoolServer
 * 阻塞
 * @Author xuedong.wang
 * @Date 17/6/16.
 */
public class HelloTSimpleClient {
    public static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 8080;
    public static final int TIMEOUT = 30000;

    public static void main(String[] args) throws TException {
        // 设置传输通道
        TTransport transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
        // 协议要和服务端一致
        //使用二进制协议
        TProtocol protocol = new TBinaryProtocol(transport);
        //创建Client
        HelloService.Client client = new HelloService.Client(protocol);
        transport.open();
        String result = client.hello("jack");
        System.out.println("result : " + result);
        //关闭资源
        transport.close();
    }

}
