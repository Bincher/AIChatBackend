package com.example.AIChat.dto.response.friend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.AIChat.common.ResponseCode;
import com.example.AIChat.common.ResponseMessage;
import com.example.AIChat.dto.response.ResponseDto;

public class InviteFriendResponseDto extends ResponseDto{
    private InviteFriendResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<InviteFriendResponseDto> success(){
        InviteFriendResponseDto result = new InviteFriendResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> existedFriend(){
        ResponseDto result = new ResponseDto(ResponseCode.EXISTED_FRIEND, ResponseMessage.EXISTED_FRIEND);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> duplicatedPosting(){
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATED_POSTING, ResponseMessage.DUPLICATED_POSTING);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    
}
