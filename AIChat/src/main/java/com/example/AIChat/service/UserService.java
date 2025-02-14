package com.example.AIChat.service;

import org.springframework.http.ResponseEntity;

import com.example.AIChat.dto.request.user.GetUserRequestDto;
import com.example.AIChat.dto.response.user.GetSignInUserResponseDto;
import com.example.AIChat.dto.response.user.GetUserResponseDto;

public interface UserService {
    ResponseEntity<? super GetSignInUserResponseDto>getSignInUser(String loginId);
    ResponseEntity<? super GetUserResponseDto>getUser(GetUserRequestDto dto, String loginId);
}
