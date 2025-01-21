package com.example.AIChat.service;

import org.springframework.http.ResponseEntity;

import com.example.AIChat.dto.request.auth.IdCheckRequestDto;
import com.example.AIChat.dto.response.auth.IdCheckResponseDto;

public interface AuthService {
    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
}
