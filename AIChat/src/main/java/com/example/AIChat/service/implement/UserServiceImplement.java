package com.example.AIChat.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.dto.response.user.GetSignInUserResponseDto;
import com.example.AIChat.entity.UserEntity;
import com.example.AIChat.repository.UserRepository;
import com.example.AIChat.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService{

    private final UserRepository userRepository;

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

}
