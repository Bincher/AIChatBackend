package com.example.AIChat.config;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.AIChat.service.implement.ChatServiceImplement;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSocket
public class WebSocketConfig extends TextWebSocketHandler implements WebSocketConfigurer {

    private final ChatServiceImplement chatService;

    // WebSocket 세션 관리
    private static final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

    public WebSocketConfig(ChatServiceImplement chatService) {
        this.chatService = chatService;
    }

    // WebSocket 엔드포인트 등록
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(this, "/ws/chat").setAllowedOrigins("*");
    }

    // 클라이언트가 WebSocket에 연결되었을 때 호출
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("New session added: " + session.getId());
    }

    // 클라이언트가 WebSocket 연결을 종료했을 때 호출
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("Session removed: " + session.getId());
    }

    // 클라이언트로부터 메시지를 수신했을 때 호출
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            System.out.println("Received WebSocket message: " + message.getPayload());

            // 메시지 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> data = objectMapper.readValue(message.getPayload(), Map.class);

            String roomId = data.get("roomId");
            String sender = data.get("sender");
            String content = data.get("content");

            System.out.println("Parsed message - roomId: " + roomId + ", sender: " + sender + ", content: " + content);

            // MongoDB에 저장 (ChatService 사용)
            chatService.saveMessage(roomId, sender, content);

            // 다른 클라이언트로 브로드캐스트
            for (WebSocketSession sess : sessions) {
                if (sess.isOpen()) {
                    sess.sendMessage(new TextMessage(objectMapper.writeValueAsString(data)));
                }
            }
        } catch (Exception e) {
            System.err.println("Error while processing WebSocket message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}