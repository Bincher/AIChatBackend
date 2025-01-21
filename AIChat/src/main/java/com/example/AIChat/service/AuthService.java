package com.example.AIChat.service;

import org.springframework.http.ResponseEntity;

import com.example.AIChat.dto.request.auth.CheckCertificationRequestDto;
import com.example.AIChat.dto.request.auth.EmailCertificationRequestDto;
import com.example.AIChat.dto.request.auth.IdCheckRequestDto;
import com.example.AIChat.dto.request.auth.SignUpRequestDto;
import com.example.AIChat.dto.response.auth.CheckCertificationResponseDto;
import com.example.AIChat.dto.response.auth.EmailCertificationResponseDto;
import com.example.AIChat.dto.response.auth.IdCheckResponseDto;
import com.example.AIChat.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);
    ResponseEntity<? super SignUpResponseDto> SignUp(SignUpRequestDto dto);
}
