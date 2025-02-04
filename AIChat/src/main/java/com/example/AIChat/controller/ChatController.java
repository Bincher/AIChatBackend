package com.example.AIChat.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AIChat.dto.object.ChatRoomWithUsersDto;
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

    @GetMapping("/{userId}")
    public ResponseEntity<List<ChatRoomWithUsersDto>> getChatRooms(@PathVariable Integer userId) {
        List<ChatRoomWithUsersDto> response = chatService.getUserChatRoomsWithUsers(userId);
        return ResponseEntity.ok(response);
    }
    
    
}