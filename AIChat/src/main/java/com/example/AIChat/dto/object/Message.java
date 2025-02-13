package com.example.AIChat.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Message {
    private String role;
    private String content;
}
