package com.example.AIChat.service;

import java.util.List;

import com.example.AIChat.dto.object.ChatRoomWithUsersDto;

public interface ChatService {
    public void saveMessage(String roomId, String sender, String content);
    List<ChatRoomWithUsersDto> getUserChatRoomsWithUsers(Integer userId);
}
