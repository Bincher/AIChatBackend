package com.example.AIChat.entity;

import com.mongodb.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="chat_list_view")
@Table(name="chat_list_view")
@Immutable
public class ChatListViewEntity {
    
    @Id
    @Column(name = "chat_room_id")
    private int chatRoomId;

    private String chatRoomName;
    private int userId;
    private String userNickname;
    private String userProfileImage;

}
