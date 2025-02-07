package com.example.AIChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AIChat.entity.FriendshipEntity;

public interface FriendshipRepository extends JpaRepository<FriendshipEntity, Integer> {
    FriendshipEntity findByUserIdAndFriendId(Integer userId, Integer friendId);
}