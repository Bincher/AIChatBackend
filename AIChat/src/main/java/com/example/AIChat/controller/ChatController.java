package com.example.AIChat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AIChat.entity.MessageEntity;
import com.example.AIChat.repository.MessageRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final MessageRepository messageRepository;

    @GetMapping("/")
    public String index(){
        return "error";
    }

    @GetMapping("/history/{roomId}")
    public List<MessageEntity> getChatHistory(@PathVariable String roomId) {
        System.out.println("Fetching chat history for roomId: " + roomId);
        return messageRepository.findAllByRoomId(roomId);
    }
    
}