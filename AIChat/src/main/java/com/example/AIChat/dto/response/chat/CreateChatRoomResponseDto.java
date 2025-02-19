package com.example.AIChat.dto.response.chat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.AIChat.common.ResponseCode;
import com.example.AIChat.common.ResponseMessage;
import com.example.AIChat.dto.response.ResponseDto;

public class CreateChatRoomResponseDto extends ResponseDto{

    private CreateChatRoomResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<CreateChatRoomResponseDto> success(){
        CreateChatRoomResponseDto result = new CreateChatRoomResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
