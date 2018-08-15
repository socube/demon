package springwebsocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Description
 * @Author xuedong.wang
 * @Date 17/12/7.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig {

    @Bean
    public WebSocketHandler myHandler() {
        return new MyWebSocketHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/ws").addInterceptors(new HandShake());
        registry.addHandler(myHandler(), "/ws/sockjs").addInterceptors(new HandShake()).withSockJS();
    }

}
