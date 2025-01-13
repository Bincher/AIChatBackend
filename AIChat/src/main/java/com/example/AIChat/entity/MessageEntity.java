package com.example.AIChat.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "messages")
@Getter @Setter
public class MessageEntity {
    @Id
    private String id;

    private String roomId; // MySQL ChatRoom ID와 매핑
    private String sender;
    private String content;
    private String timestamp; // ISO-8601 형식으로 저장 (예: 2023-10-01T12:34:56Z)
}
