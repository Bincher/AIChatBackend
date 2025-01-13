package com.example.AIChat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.AIChat.entity.MessageEntity;

public interface MessageRepository extends MongoRepository<MessageEntity, String> {
    List<MessageEntity> findAllByRoomId(String roomId);
}