package com.example.AIChat.service.implement;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.AIChat.dto.object.ChatRoomWithUsersDto;
import com.example.AIChat.dto.object.ChatUserDto;
import com.example.AIChat.dto.projection.ChatRoomUserProjection;
import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.dto.response.chat.GetChatRoomListResponseDto;
import com.example.AIChat.entity.MessageEntity;
import com.example.AIChat.entity.UserEntity;
import com.example.AIChat.repository.ChatRoomRepository;
import com.example.AIChat.repository.MessageRepository;
import com.example.AIChat.repository.UserRepository;
import com.example.AIChat.service.ChatService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImplement implements ChatService{
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public Integer getUserIdByLoginId(String loginId) {
        return userRepository.findIdByLoginId(loginId)
                .map(UserEntity::getId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found with title: " + loginId));
    }

    @Override
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

    @Override
    public ResponseEntity<? super GetChatRoomListResponseDto> getUserChatRooms(String loginId) {

        try {
            Integer userId = getUserIdByLoginId(loginId);

            List<ChatRoomUserProjection> projections = chatRoomRepository.findChatRoomsWithUsers(userId);

            Map<Integer, ChatRoomWithUsersDto> resultMap = new HashMap<>();
            for (ChatRoomUserProjection projection : projections) {
                resultMap.computeIfAbsent(projection.getChatRoomId(), id ->
                    new ChatRoomWithUsersDto(id, projection.getRoomName(), new ArrayList<>())
                ).getUsers().add(new ChatUserDto(projection.getNickname(), projection.getProfileImage()));
            }

            List<ChatRoomWithUsersDto> chatRooms = new ArrayList<>(resultMap.values());

            return GetChatRoomListResponseDto.success(chatRooms);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
    
}