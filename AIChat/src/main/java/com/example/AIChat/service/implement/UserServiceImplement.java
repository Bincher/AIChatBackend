package com.example.AIChat.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.AIChat.dto.request.user.GetUserRequestDto;
import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.dto.response.user.GetSignInUserResponseDto;
import com.example.AIChat.dto.response.user.GetUserResponseDto;
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
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String loginId){
        
        UserEntity userEntity = null;

        try{
            userEntity = userRepository.findByLoginId(loginId);
            if(userEntity == null) return GetSignInUserResponseDto.notExistUser();

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(GetUserRequestDto dto, String loginId) {
        
        UserEntity userEntity = null;
        boolean isFriend = false;

        try{
            userEntity = userRepository.findByNickname(dto.getNickname());
            if(userEntity == null) return GetSignInUserResponseDto.notExistUser();

            Integer friendId = getUserIdByNickname(dto.getNickname());
            Integer userId = getUserIdByLoginId(loginId);
            isFriend = friendshipRepository.existsByUserIdAndFriendId(userId, friendId);

        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        if(isFriend) return GetUserResponseDto.success(userEntity, true);
        else return GetUserResponseDto.success(userEntity, false);
        
    }

}
