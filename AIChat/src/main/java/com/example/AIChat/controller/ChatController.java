package com.example.AIChat.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.AIChat.entity.MessageEntity;
import com.example.AIChat.repository.MessageRepository;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ChatController {

    private final MessageRepository messageRepository;

    @GetMapping("/")
    public String index(){
        return "error";
    }

    @GetMapping("/{id}")
    public String chattingRoom(@PathVariable String id, HttpSession session, Model model){
        if(id.equals("guest")){
            model.addAttribute("name", "guest");
        }else if(id.equals("master")){
            model.addAttribute("name", "master");
        }else if(id.equals("loose")){
            model.addAttribute("name", "loose");
        }else {
            return "error";
        }
        return "chattingRoom2";
    }

    @GetMapping("/history/{roomId}")
    public List<MessageEntity> getChatHistory(@PathVariable String roomId) {
        return messageRepository.findAllByRoomId(roomId);
    }
    
}