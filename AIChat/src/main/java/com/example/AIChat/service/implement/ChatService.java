package com.example.AIChat.service.implement;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.example.AIChat.entity.MessageEntity;
import com.example.AIChat.repository.MessageRepository;

@Service
public class ChatService {
    private final MessageRepository messageRepository;

    public ChatService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessage(String roomId, String sender, String content) {
        System.out.println("ChatService.saveMessage called with roomId=" + roomId + ", sender=" + sender + ", content=" + content);

        MessageEntity message = new MessageEntity();
        message.setRoomId(roomId);
        message.setSender(sender);
        message.setContent(content);
        message.setTimestamp(Instant.now().toString());

        System.out.println("Saving to MongoDB: " + message);
        messageRepository.save(message);
        System.out.println("Message saved successfully");
    }
}