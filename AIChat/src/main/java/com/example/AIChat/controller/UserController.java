package com.example.AIChat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AIChat.dto.request.user.GetUserListRequestDto;
import com.example.AIChat.dto.request.user.PatchFriendRequestDto;
import com.example.AIChat.dto.request.user.PostFriendRequestDto;
import com.example.AIChat.dto.response.user.GetUserListResponseDto;
import com.example.AIChat.dto.response.user.PatchFriendResponseDto;
import com.example.AIChat.dto.response.user.PostFriendResponseDto;
import com.example.AIChat.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    
    @PostMapping("/nickname")
    public ResponseEntity<? super GetUserListResponseDto> getUserList(
        @RequestBody @Valid GetUserListRequestDto requestBody
    ) {
        ResponseEntity<? super GetUserListResponseDto> response = userService.getUserList(requestBody);
        return response;
    }

    @PostMapping("/friend/send")
    public ResponseEntity<? super PostFriendResponseDto> postFriend(
        @AuthenticationPrincipal String loginId,
        @RequestBody @Valid PostFriendRequestDto requestBody
    ) {
        ResponseEntity<? super PostFriendResponseDto> response = userService.postFriend(requestBody, loginId);
        return response;
    }

    @PatchMapping("/friend/request")
    public ResponseEntity<? super PatchFriendResponseDto> patchFriend(
        @AuthenticationPrincipal String loginId,
        @RequestBody @Valid PatchFriendRequestDto requestBody
    ) {
        ResponseEntity<? super PatchFriendResponseDto> response = userService.patchFriend(requestBody, loginId);
        return response;
    }
}
