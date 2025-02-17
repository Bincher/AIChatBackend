package com.example.AIChat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AIChat.dto.request.friend.AcceptFriendRequestDto;
import com.example.AIChat.dto.request.friend.InviteFriendRequestDto;
import com.example.AIChat.dto.response.friend.DeleteFriendResponseDto;
import com.example.AIChat.dto.response.friend.GetInviteFriendResponseDto;
import com.example.AIChat.dto.response.friend.GetMyFriendResponseDto;
import com.example.AIChat.dto.response.friend.GetUserListResponseDto;
import com.example.AIChat.dto.response.friend.AcceptFriendResponseDto;
import com.example.AIChat.dto.response.friend.InviteFriendResponseDto;
import com.example.AIChat.service.FriendService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/friend")
public class FriendController {

    private final FriendService friendService;
    
    @GetMapping("/{nickname}")
    public ResponseEntity<? super GetUserListResponseDto> getUserList(
        @AuthenticationPrincipal String loginId,
        @PathVariable String nickname
    ) {
        ResponseEntity<? super GetUserListResponseDto> response = friendService.getUserList(nickname, loginId);
        return response;
    }

    @PostMapping("/request")
    public ResponseEntity<? super InviteFriendResponseDto> inviteFriend(
        @AuthenticationPrincipal String loginId,
        @RequestBody @Valid InviteFriendRequestDto requestBody
    ) {
        ResponseEntity<? super InviteFriendResponseDto> response = friendService.inviteFriend(requestBody, loginId);
        return response;
    }

    @PatchMapping("/response")
    public ResponseEntity<? super AcceptFriendResponseDto> acceptFriend(
        @AuthenticationPrincipal String loginId,
        @RequestBody @Valid AcceptFriendRequestDto requestBody
    ) {
        ResponseEntity<? super AcceptFriendResponseDto> response = friendService.acceptFriend(requestBody, loginId);
        return response;
    }

    @GetMapping("/myfriend")
    public ResponseEntity<? super GetMyFriendResponseDto> getMyFriend(
        @AuthenticationPrincipal String loginId
    ) {
        ResponseEntity<? super GetMyFriendResponseDto> response = friendService.getMyFriend(loginId);
        return response;
    }

    @DeleteMapping("/drop/{nickname}")
    public ResponseEntity<? super DeleteFriendResponseDto> deleteFriend(
        @AuthenticationPrincipal String loginId,
        @PathVariable String nickname
    ) {
        ResponseEntity<? super DeleteFriendResponseDto> response = friendService.deleteFriend(nickname, loginId);
        return response;
    }

    @GetMapping("/invite")
    public ResponseEntity<? super GetInviteFriendResponseDto> inviteFriend(
        @AuthenticationPrincipal String loginId
    ) {
        ResponseEntity<? super GetInviteFriendResponseDto> response = friendService.getInviteFriend(loginId);
        return response;
    }
}
