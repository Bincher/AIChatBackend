package com.example.AIChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AIChat.entity.ChatRoomEntity;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Integer> {

}