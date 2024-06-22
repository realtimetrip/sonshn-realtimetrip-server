package com.venti.realtimetrip.config.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * message broker for STOMP
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry) {
        // Message Subscription Url
        messageBrokerRegistry.enableSimpleBroker("/sub");
        // Message Publication Url
        messageBrokerRegistry.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry
                //.addEndpoint("/ws") // Add Endpoint Path
                .addEndpoint("/stomp/chat") // Add Endpoint Path
                .setAllowedOriginPatterns("*") // CORS 설정
                .withSockJS(); // url 변경: ws -> http
    }

}

