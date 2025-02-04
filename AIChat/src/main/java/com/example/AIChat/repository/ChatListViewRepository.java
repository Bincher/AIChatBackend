package com.example.AIChat.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.AIChat.entity.ChatListViewEntity;

public interface ChatListViewRepository extends JpaRepository<ChatListViewEntity, Integer>{

    List<ChatListViewEntity> findByUserIdOrderByChatRoomNameDesc(int userId);

    List<ChatListViewEntity> findByUserIdOrderByChatRoomIdDesc(int userId);
    
}
