package com.example.AIChat.entity;

import com.example.AIChat.dto.request.chat.CreateChatRoomRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="chat_room")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ChatRoomEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String roomName;

    public ChatRoomEntity(CreateChatRoomRequestDto dto){
        this.roomName = dto.getRoomName();
    }
}
