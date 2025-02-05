package com.example.AIChat.service;

import org.springframework.http.ResponseEntity;

import com.example.AIChat.dto.response.chat.GetChatRoomListResponseDto;

public interface ChatService {
    void saveMessage(String roomId, String sender, String content);
    ResponseEntity<? super GetChatRoomListResponseDto> getUserChatRooms(String loginId);
}
