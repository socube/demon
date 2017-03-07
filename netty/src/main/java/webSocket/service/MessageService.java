package webSocket.service;

import webSocket.dto.Response;
import webSocket.entity.Client;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/3/7.
 */
public class MessageService {

    public static Response sendMessage(Client client, String message) {
        Response res = new Response();
        res.getData().put("id", client.getId());
        res.getData().put("message", message);
        res.getData().put("ts", System.currentTimeMillis());// 返回毫秒数
        return res;
    }
}
