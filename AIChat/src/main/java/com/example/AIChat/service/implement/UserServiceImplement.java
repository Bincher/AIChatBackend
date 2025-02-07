package com.example.AIChat.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.AIChat.dto.request.user.GetUserListRequestDto;
import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.dto.response.user.GetUserListResponseDto;
import com.example.AIChat.entity.UserEntity;
import com.example.AIChat.repository.UserRepository;
import com.example.AIChat.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService{

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetUserListResponseDto> getUserList(GetUserListRequestDto dto) {
        
        List<UserEntity> userEntities = new ArrayList<>();

        try{
            // boolean existsUser = userRepository.existsByNickname(dto.getNickname());
            // if(!existsUser) return GetUserListResponseDto.noExistUser();

            userEntities = userRepository.findByNicknameContainsOrderById(dto.getNickname());
        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserListResponseDto.success(userEntities);
    }
    
}
