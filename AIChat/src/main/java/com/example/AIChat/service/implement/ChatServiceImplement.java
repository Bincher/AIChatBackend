package com.example.AIChat.service.implement;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.dto.response.chat.GetChatListResponseDto;
import com.example.AIChat.entity.ChatRoomListViewEntity;
import com.example.AIChat.entity.MessageEntity;
import com.example.AIChat.repository.ChatRoomListViewRepository;
import com.example.AIChat.repository.MessageRepository;
import com.example.AIChat.repository.UserRepository;
import com.example.AIChat.service.ChatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImplement implements ChatService{
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRoomListViewRepository chatListViewRepository;

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
    public ResponseEntity<? super GetChatListResponseDto> getChatList(String loginId) {
        List<ChatRoomListViewEntity> chatListViewEntities = new ArrayList<>();

        try{
            boolean existedUser = userRepository.existsByLoginId(loginId);
            if(!existedUser) return GetChatListResponseDto.noExistUser();

            chatListViewEntities = chatListViewRepository.findByUserLoginIdOrderByChatRoomIdDesc(loginId);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetChatListResponseDto.success(chatListViewEntities);
    }

    
}