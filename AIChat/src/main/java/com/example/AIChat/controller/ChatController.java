package com.example.AIChat.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AIChat.dto.response.chat.GetChatRoomListResponseDto;
import com.example.AIChat.entity.MessageEntity;
import com.example.AIChat.repository.MessageRepository;
import com.example.AIChat.service.ChatService;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final MessageRepository messageRepository;
    private final ChatService chatService;

    @GetMapping("/")
    public String index(){
        return "error";
    }

    @GetMapping("/history/{roomId}")
    public List<MessageEntity> getChatHistory(@PathVariable String roomId) {
        System.out.println("Fetching chat history for roomId: " + roomId);
        return messageRepository.findAllByRoomId(roomId);
    }

    @GetMapping("/chat-room")
    public ResponseEntity<? super GetChatRoomListResponseDto> getChatRooms(
        @AuthenticationPrincipal String loginId
    ) {
        ResponseEntity<? super GetChatRoomListResponseDto> response = chatService.getUserChatRooms(loginId);
        return response;
    }
}