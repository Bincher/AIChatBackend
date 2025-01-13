package com.example.AIChat.config;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.AIChat.common.CustomWebSocketHandler;
import com.example.AIChat.service.implement.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    ChatService chatService;

    @Autowired
    private CustomWebSocketHandler customWebSocketHandler;

    private static Set<WebSocketSession> numSet = ConcurrentHashMap.newKeySet();

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(customWebSocketHandler, "ws/chat").setAllowedOrigins("*");
    }

    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        
        // 예: JSON으로 수신된 메시지를 파싱하여 저장 (Jackson 사용)
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> data = objectMapper.readValue(payload, Map.class);

        String roomId = data.get("roomId");
        String sender = data.get("sender");
        String content = data.get("content");

        // MongoDB에 메시지 저장
        chatService.saveMessage(roomId, sender, content);

        // 다른 클라이언트로 브로드캐스트 (기존 로직 유지)
        for (WebSocketSession sess : numSet) {
            if (sess.isOpen()) {
                sess.sendMessage(new TextMessage(payload));
            }
        }
    }

}