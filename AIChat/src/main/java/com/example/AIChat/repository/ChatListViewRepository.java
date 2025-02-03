package com.example.AIChat.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.AIChat.entity.ChatListViewEntity;

import java.util.List;

public interface ChatListViewRepository extends JpaRepository<ChatListViewEntity, Integer>{
    
}
