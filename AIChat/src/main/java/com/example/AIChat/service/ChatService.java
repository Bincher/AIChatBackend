package com.example.AIChat.service;

import org.springframework.http.ResponseEntity;

import com.example.AIChat.dto.request.chat.CreateChatRoomRequestDto;
import com.example.AIChat.dto.response.chat.GetChatRoomListResponseDto;
import com.example.AIChat.dto.response.chat.CreateChatRoomResponseDto;

public interface ChatService {
    void saveMessage(String roomId, String sender, String content);
    ResponseEntity<? super GetChatRoomListResponseDto> getUserChatRooms(String loginId);
    ResponseEntity<? super CreateChatRoomResponseDto> createChatRoom(CreateChatRoomRequestDto dto, String loginId);
}
