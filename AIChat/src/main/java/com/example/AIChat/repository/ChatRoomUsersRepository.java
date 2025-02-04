package com.example.AIChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AIChat.entity.ChatRoomUsersEntity;

public interface ChatRoomUsersRepository extends JpaRepository<ChatRoomUsersEntity, Integer>{

    

}

