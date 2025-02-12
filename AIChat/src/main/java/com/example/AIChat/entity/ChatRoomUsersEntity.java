package com.example.AIChat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "chat_room_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomUsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "chat_room_id", nullable = false)
    private int chatRoomId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    public ChatRoomUsersEntity(Integer chatRoomId, Integer userId) {
        this.chatRoomId = chatRoomId;
        this.userId = userId;
    }

}
