package com.example.AIChat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AIChat.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}