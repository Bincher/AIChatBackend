package com.example.AIChat.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.AIChat.entity.ChatRoomListViewEntity;

public interface ChatRoomListViewRepository extends JpaRepository<ChatRoomListViewEntity, Integer>{

    List<ChatRoomListViewEntity> findByUserLoginIdOrderByChatRoomIdDesc(String loginId);
    
}
