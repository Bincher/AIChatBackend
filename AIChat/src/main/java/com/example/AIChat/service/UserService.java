package com.example.AIChat.service;

import org.springframework.http.ResponseEntity;

import com.example.AIChat.dto.request.user.GetUserListRequestDto;
import com.example.AIChat.dto.request.user.PostFriendRequestDto;
import com.example.AIChat.dto.response.user.GetUserListResponseDto;
import com.example.AIChat.dto.response.user.PostFriendResponseDto;

public interface UserService {
    ResponseEntity<? super GetUserListResponseDto>getUserList(GetUserListRequestDto dto);
    ResponseEntity<? super PostFriendResponseDto>postFriend(PostFriendRequestDto dto, String loginId);
}
