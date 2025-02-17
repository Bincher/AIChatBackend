package com.example.AIChat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AIChat.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByLoginId(String loginId);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    UserEntity findByLoginId(String loginId);

    Optional<UserEntity> findIdByLoginId(String loginId);

    Optional<UserEntity> findIdByNickname(String nickname);

    Integer getIdByLoginId(String loginId);

    boolean existsById(@SuppressWarnings("null") Integer userId);

    List<UserEntity> findByNicknameContainsOrderById(String nickname);

    UserEntity findByNickname(String nickname);

}