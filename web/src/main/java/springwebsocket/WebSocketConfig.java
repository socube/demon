//package springwebsocket;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
///**
// * @Description
// * @Author xuedong.wang
// * @Date 17/12/7.
// */
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        //添加这个Endpoint，这样在网页中就可以通过websocket连接上服务了
//        registry.addEndpoint("/coordination").withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        System.out.println("服务器启动成功");
//        //这里设置的simple broker是指可以订阅的地址，也就是服务器可以发送的地址
//        /**
//         * userChat 用于用户聊天
//         */
//        config.enableSimpleBroker("/userChat");
//        config.setApplicationDestinationPrefixes("/app");
//    }
//
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration channelRegistration) {
//    }
//
//    @Override
//    public void configureClientOutboundChannel(ChannelRegistration channelRegistration) {
//    }
//}
