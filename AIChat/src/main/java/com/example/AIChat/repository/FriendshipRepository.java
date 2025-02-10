package com.example.AIChat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.AIChat.entity.FriendshipEntity;

public interface FriendshipRepository extends JpaRepository<FriendshipEntity, Integer> {

    boolean existsByUserIdAndFriendId(Integer userId, Integer friendId);

    FriendshipEntity findByUserIdAndFriendId(Integer userId, Integer friendId);

    @Query(value = "SELECT * FROM friendship WHERE user_id = :currentUserId AND status = 'ACCEPTED'", nativeQuery = true)
    List<FriendshipEntity> findAcceptedFriends(@Param("currentUserId") Integer currentUserId);

    @Query(value = "SELECT * FROM friendship WHERE friend_id = :currentUserId AND status = 'PENDING'", nativeQuery = true)
    List<FriendshipEntity> findInvitedUser(@Param("currentUserId") Integer currentUserId);
}