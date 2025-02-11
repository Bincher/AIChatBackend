package com.example.AIChat.service;

import org.springframework.http.ResponseEntity;

import com.example.AIChat.dto.request.friend.DeleteFriendRequestDto;
import com.example.AIChat.dto.request.friend.PatchFriendRequestDto;
import com.example.AIChat.dto.request.friend.PostFriendRequestDto;
import com.example.AIChat.dto.response.friend.DeleteFriendResponseDto;
import com.example.AIChat.dto.response.friend.GetInviteFriendResponseDto;
import com.example.AIChat.dto.response.friend.GetMyFriendResponseDto;
import com.example.AIChat.dto.response.friend.GetUserListResponseDto;
import com.example.AIChat.dto.response.friend.PatchFriendResponseDto;
import com.example.AIChat.dto.response.friend.PostFriendResponseDto;

public interface FriendService {
    ResponseEntity<? super GetUserListResponseDto>getUserList(String nickname, String loginId);
    ResponseEntity<? super PostFriendResponseDto>postFriend(PostFriendRequestDto dto, String loginId);
    ResponseEntity<? super PatchFriendResponseDto>patchFriend(PatchFriendRequestDto dto, String loginId);
    ResponseEntity<? super GetMyFriendResponseDto>getMyFriend(String loginId);
    ResponseEntity<? super DeleteFriendResponseDto>deleteFriend(DeleteFriendRequestDto dto, String loginId);
    ResponseEntity<? super GetInviteFriendResponseDto>getInviteFriend(String loginId);
    
}
