package com.example.AIChat.dto.response.chat;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.AIChat.common.ResponseCode;
import com.example.AIChat.common.ResponseMessage;
import com.example.AIChat.dto.object.ChatRoomWithUsersDto;
import com.example.AIChat.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class GetChatRoomListResponseDto extends ResponseDto {

    private List<ChatRoomWithUsersDto> chatRooms;

    private GetChatRoomListResponseDto() {
        super();
    }

    private GetChatRoomListResponseDto(List<ChatRoomWithUsersDto> chatRooms) {
        super();
        this.chatRooms = chatRooms;
    }

    public static ResponseEntity<GetChatRoomListResponseDto> success(List<ChatRoomWithUsersDto> chatRooms) {
        GetChatRoomListResponseDto responseBody = new GetChatRoomListResponseDto(chatRooms);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistedUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}