package com.example.AIChat.service;

import org.springframework.http.ResponseEntity;

import com.example.AIChat.dto.response.chat.GetChatListResponseDto;

public interface ChatService {
    public void saveMessage(String roomId, String sender, String content);
    ResponseEntity<? super GetChatListResponseDto> getChatList(String loginId);
}
