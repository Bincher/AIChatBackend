package com.example.AIChat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.AIChat.dto.projection.ChatRoomUserProjection;
import com.example.AIChat.entity.ChatRoomEntity;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Integer> {

    @Query(value = """
        SELECT cr.id AS chatRoomId, cr.room_name AS roomName, u.nickname AS nickname, u.profile_image AS profileImage 
        FROM chat_room_users cru
        JOIN chat_room cr ON cru.chat_room_id = cr.id
        JOIN user u ON cru.user_id = u.id
        WHERE cru.chat_room_id IN (
            SELECT chat_room_id 
            FROM chat_room_users 
            WHERE user_id = :userId
        )
        """, nativeQuery = true)
    List<ChatRoomUserProjection> findChatRoomsWithUsers(@Param("userId") Integer userId);

    @Query(value = """
        SELECT cr.id AS chatRoomId, cr.room_name AS roomName, u.nickname AS nickname, u.profile_image AS profileImage 
        FROM chat_room_users cru
        JOIN chat_room cr ON cru.chat_room_id = cr.id
        JOIN user u ON cru.user_id = u.id
        WHERE cru.chat_room_id = :chatRoomId
    """, nativeQuery = true)
    List<ChatRoomUserProjection> findChatRoomWithRoomId(@Param("chatRoomId") Integer chatRoomId);
}