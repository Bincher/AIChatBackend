package com.example.AIChat.service;

import org.springframework.http.ResponseEntity;

import com.example.AIChat.dto.request.friend.AcceptFriendRequestDto;
import com.example.AIChat.dto.request.friend.InviteFriendRequestDto;
import com.example.AIChat.dto.response.friend.DeleteFriendResponseDto;
import com.example.AIChat.dto.response.friend.GetInviteFriendResponseDto;
import com.example.AIChat.dto.response.friend.GetMyFriendResponseDto;
import com.example.AIChat.dto.response.friend.GetUserListResponseDto;
import com.example.AIChat.dto.response.friend.AcceptFriendResponseDto;
import com.example.AIChat.dto.response.friend.InviteFriendResponseDto;

public interface FriendService {
    ResponseEntity<? super GetUserListResponseDto>getUserList(String nickname, String loginId);
    ResponseEntity<? super InviteFriendResponseDto>inviteFriend(InviteFriendRequestDto dto, String loginId);
    ResponseEntity<? super AcceptFriendResponseDto>acceptFriend(AcceptFriendRequestDto dto, String loginId);
    ResponseEntity<? super GetMyFriendResponseDto>getMyFriend(String loginId);
    ResponseEntity<? super DeleteFriendResponseDto>deleteFriend(String nickname, String loginId);
    ResponseEntity<? super GetInviteFriendResponseDto>getInviteFriend(String loginId);
    
}
