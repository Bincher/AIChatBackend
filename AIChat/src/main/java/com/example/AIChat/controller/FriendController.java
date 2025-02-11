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

import com.example.AIChat.dto.request.friend.DeleteFriendRequestDto;
import com.example.AIChat.dto.request.friend.PatchFriendRequestDto;
import com.example.AIChat.dto.request.friend.PostFriendRequestDto;
import com.example.AIChat.dto.response.friend.DeleteFriendResponseDto;
import com.example.AIChat.dto.response.friend.GetInviteFriendResponseDto;
import com.example.AIChat.dto.response.friend.GetMyFriendResponseDto;
import com.example.AIChat.dto.response.friend.GetUserListResponseDto;
import com.example.AIChat.dto.response.friend.PatchFriendResponseDto;
import com.example.AIChat.dto.response.friend.PostFriendResponseDto;
import com.example.AIChat.service.FriendService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/friend")
public class FriendController {

    private final FriendService userService;
    
    @GetMapping("/{nickname}")
    public ResponseEntity<? super GetUserListResponseDto> getUserList(
        @AuthenticationPrincipal String loginId,
        @PathVariable String nickname
    ) {
        ResponseEntity<? super GetUserListResponseDto> response = userService.getUserList(nickname, loginId);
        return response;
    }

    @PostMapping("/send")
    public ResponseEntity<? super PostFriendResponseDto> postFriend(
        @AuthenticationPrincipal String loginId,
        @RequestBody @Valid PostFriendRequestDto requestBody
    ) {
        ResponseEntity<? super PostFriendResponseDto> response = userService.postFriend(requestBody, loginId);
        return response;
    }

    @PatchMapping("/request")
    public ResponseEntity<? super PatchFriendResponseDto> patchFriend(
        @AuthenticationPrincipal String loginId,
        @RequestBody @Valid PatchFriendRequestDto requestBody
    ) {
        ResponseEntity<? super PatchFriendResponseDto> response = userService.patchFriend(requestBody, loginId);
        return response;
    }

    @GetMapping("/")
    public ResponseEntity<? super GetMyFriendResponseDto> getMyFriend(
        @AuthenticationPrincipal String loginId
    ) {
        ResponseEntity<? super GetMyFriendResponseDto> response = userService.getMyFriend(loginId);
        return response;
    }

    @DeleteMapping("/")
    public ResponseEntity<? super DeleteFriendResponseDto> deleteFriend(
        @AuthenticationPrincipal String loginId,
        @RequestBody @Valid DeleteFriendRequestDto requestBody
    ) {
        ResponseEntity<? super DeleteFriendResponseDto> response = userService.deleteFriend(requestBody, loginId);
        return response;
    }

    @GetMapping("/invite")
    public ResponseEntity<? super GetInviteFriendResponseDto> inviteFriend(
        @AuthenticationPrincipal String loginId
    ) {
        ResponseEntity<? super GetInviteFriendResponseDto> response = userService.getInviteFriend(loginId);
        return response;
    }
}
