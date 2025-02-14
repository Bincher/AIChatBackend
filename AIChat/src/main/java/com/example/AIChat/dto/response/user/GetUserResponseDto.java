package com.example.AIChat.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.AIChat.common.ResponseCode;
import com.example.AIChat.common.ResponseMessage;
import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserResponseDto extends ResponseDto{
    private String nickname;
    private String profileImage;
    private boolean isFriend;

    private GetUserResponseDto(UserEntity userEntity, boolean isFriend){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.nickname = userEntity.getNickname();
        this.profileImage = userEntity.getProfileImage();
        this.isFriend = isFriend;
    }

    public static ResponseEntity<GetUserResponseDto> success(UserEntity userEntity, boolean isFriend){
        GetUserResponseDto result = new GetUserResponseDto(userEntity, isFriend);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
