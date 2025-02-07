package com.example.AIChat.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.AIChat.dto.request.user.GetUserListRequestDto;
import com.example.AIChat.dto.request.user.PostFriendRequestDto;
import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.dto.response.user.GetUserListResponseDto;
import com.example.AIChat.dto.response.user.PostFriendResponseDto;
import com.example.AIChat.entity.FriendshipEntity;
import com.example.AIChat.entity.UserEntity;
import com.example.AIChat.repository.FriendshipRepository;
import com.example.AIChat.repository.UserRepository;
import com.example.AIChat.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService{

    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;

    public Integer getUserIdByLoginId(String loginId) {
        return userRepository.findIdByLoginId(loginId)
                .map(UserEntity::getId)
                .orElseThrow(() -> new EntityNotFoundException(loginId));
    }

    public Integer getUserIdByNickname(String nickname) {
        return userRepository.findIdByNickname(nickname)
                .map(UserEntity::getId)
                .orElseThrow(() -> new EntityNotFoundException(nickname));
    }

    @Override
    public ResponseEntity<? super GetUserListResponseDto> getUserList(GetUserListRequestDto dto) {
        
        List<UserEntity> userEntities = new ArrayList<>();

        try{
            userEntities = userRepository.findByNicknameContainsOrderById(dto.getNickname());
        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserListResponseDto.success(userEntities);
    }

    @Override
    public ResponseEntity<? super PostFriendResponseDto> postFriend(PostFriendRequestDto dto, String loginId) {

        try {
            boolean existsFriend = userRepository.existsByNickname(dto.getNickname());
            if(!existsFriend) return GetUserListResponseDto.noExistUser();

            boolean existsUser = userRepository.existsByLoginId(loginId);
            if(!existsUser) return GetUserListResponseDto.noExistUser();

            Integer userId = getUserIdByLoginId(loginId);
            Integer friendId = getUserIdByNickname(dto.getNickname());

            FriendshipEntity friendshipEntity = new FriendshipEntity(userId, friendId);
            friendshipRepository.save(friendshipEntity);

        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostFriendResponseDto.success();
    }
    
}
