package com.example.AIChat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AIChat.dto.request.user.GetUserRequestDto;
import com.example.AIChat.dto.response.user.GetSignInUserResponseDto;
import com.example.AIChat.dto.response.user.GetUserResponseDto;
import com.example.AIChat.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    
    private final UserService userService;
    
    @GetMapping("")
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(
        @AuthenticationPrincipal String loginId
    ) {
        ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(loginId);
        return response;
    }

    @PostMapping("/other")
    public ResponseEntity<? super GetUserResponseDto> getUser(
        @AuthenticationPrincipal String loginId,
        @RequestBody @Valid GetUserRequestDto requestBody
    ) {
        ResponseEntity<? super GetUserResponseDto> response = userService.getUser(requestBody, loginId);
        return response;
    }
}
