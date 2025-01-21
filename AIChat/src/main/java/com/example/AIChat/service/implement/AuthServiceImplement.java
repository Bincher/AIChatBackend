package com.example.AIChat.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.AIChat.dto.request.auth.IdCheckRequestDto;
import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.dto.response.auth.IdCheckResponseDto;
import com.example.AIChat.repository.UserRepository;
import com.example.AIChat.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto){

        try{

            String loginId = dto.getId();
            boolean isExistId = userRepository.existsByLoginId(loginId);
            if(isExistId) return IdCheckResponseDto.duplicateId();
        
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return IdCheckResponseDto.success();
    }
    

}
