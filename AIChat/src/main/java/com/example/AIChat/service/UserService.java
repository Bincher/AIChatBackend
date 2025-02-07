package com.example.AIChat.service;

import org.springframework.http.ResponseEntity;

import com.example.AIChat.dto.request.user.GetUserListRequestDto;
import com.example.AIChat.dto.response.user.GetUserListResponseDto;

public interface UserService {
    ResponseEntity<? super GetUserListResponseDto>getUserList(GetUserListRequestDto dto);
}
