package com.example.AIChat.dto.response.friend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.AIChat.common.ResponseCode;
import com.example.AIChat.common.ResponseMessage;
import com.example.AIChat.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class DeleteFriendResponseDto extends ResponseDto{
    
    private DeleteFriendResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<DeleteFriendResponseDto> success(){
        DeleteFriendResponseDto result = new DeleteFriendResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
