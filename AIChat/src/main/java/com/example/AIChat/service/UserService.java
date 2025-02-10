package com.example.AIChat.service;

import org.springframework.http.ResponseEntity;

import com.example.AIChat.dto.request.user.DeleteFriendRequestDto;
import com.example.AIChat.dto.request.user.GetUserListRequestDto;
import com.example.AIChat.dto.request.user.PatchFriendRequestDto;
import com.example.AIChat.dto.request.user.PostFriendRequestDto;
import com.example.AIChat.dto.response.user.DeleteFriendResponseDto;
import com.example.AIChat.dto.response.user.GetInviteFriendResponseDto;
import com.example.AIChat.dto.response.user.GetMyFriendResponseDto;
import com.example.AIChat.dto.response.user.GetUserListResponseDto;
import com.example.AIChat.dto.response.user.PatchFriendResponseDto;
import com.example.AIChat.dto.response.user.PostFriendResponseDto;

public interface UserService {
    ResponseEntity<? super GetUserListResponseDto>getUserList(GetUserListRequestDto dto, String loginId);
    ResponseEntity<? super PostFriendResponseDto>postFriend(PostFriendRequestDto dto, String loginId);
    ResponseEntity<? super PatchFriendResponseDto>patchFriend(PatchFriendRequestDto dto, String loginId);
    ResponseEntity<? super GetMyFriendResponseDto>getMyFriend(String loginId);
    ResponseEntity<? super DeleteFriendResponseDto>deleteFriend(DeleteFriendRequestDto dto, String loginId);
    ResponseEntity<? super GetInviteFriendResponseDto>getInviteFriend(String loginId);
    
}
