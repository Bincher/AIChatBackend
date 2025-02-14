package com.example.AIChat.dto.response.chat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.AIChat.common.ResponseCode;
import com.example.AIChat.common.ResponseMessage;
import com.example.AIChat.dto.object.ChatRoomWithUsersDto;
import com.example.AIChat.dto.response.ResponseDto;

import lombok.Getter;

public class ChatRoomInformationResponseDto extends ResponseDto{

    @Getter
    private ChatRoomWithUsersDto chatRoom;

    private ChatRoomInformationResponseDto(ChatRoomWithUsersDto chatRoom) {
        super();
        this.chatRoom = chatRoom;
    }

    public static ResponseEntity<ChatRoomInformationResponseDto> success(ChatRoomWithUsersDto chatRoom) {
        ChatRoomInformationResponseDto responseBody = new ChatRoomInformationResponseDto(chatRoom);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistedUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistedChat() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_CHAT_ROOM, ResponseMessage.NOT_EXISTED_CHAT_ROOM);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
