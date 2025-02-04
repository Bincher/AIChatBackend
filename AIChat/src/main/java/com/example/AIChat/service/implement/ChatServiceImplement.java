package com.example.AIChat.service.implement;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.AIChat.dto.object.ChatRoomUserProjection;
import com.example.AIChat.dto.object.ChatRoomWithUsersDto;
import com.example.AIChat.dto.object.ChatUserDto;
import com.example.AIChat.entity.MessageEntity;
import com.example.AIChat.repository.ChatRoomRepository;
import com.example.AIChat.repository.MessageRepository;
import com.example.AIChat.service.ChatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImplement implements ChatService{
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;

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

    public List<ChatRoomWithUsersDto> getUserChatRoomsWithUsers(Integer userId) {
        List<ChatRoomUserProjection> projections = chatRoomRepository.findChatRoomsWithUsers(userId);

        // 데이터를 그룹화하여 반환 (채팅방별로 유저 리스트 구성)
        Map<Integer, ChatRoomWithUsersDto> resultMap = new HashMap<>();
        for (ChatRoomUserProjection projection : projections) {
            resultMap.computeIfAbsent(projection.getChatRoomId(), id -> 
                new ChatRoomWithUsersDto(id, projection.getRoomName(), new ArrayList<>())
            ).getUsers().add(new ChatUserDto(projection.getNickname(), projection.getProfileImage()));
        }

        return new ArrayList<>(resultMap.values());
    }


    
}