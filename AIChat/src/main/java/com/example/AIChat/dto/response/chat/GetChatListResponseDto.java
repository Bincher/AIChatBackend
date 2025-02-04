package com.example.AIChat.dto.response.chat;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.AIChat.common.ResponseCode;
import com.example.AIChat.common.ResponseMessage;
import com.example.AIChat.dto.object.ChatListItem;
import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.entity.ChatListViewEntity;


import lombok.Getter;

@Getter
public class GetChatListResponseDto extends ResponseDto{
    private List<ChatListItem> chatList;

    private GetChatListResponseDto(List<ChatListViewEntity> chatListViewEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.chatList = ChatListItem.getList(chatListViewEntities);
    };

    public static ResponseEntity<GetChatListResponseDto> success(List<ChatListViewEntity> chatListViewEntities){
        GetChatListResponseDto result = new GetChatListResponseDto(chatListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }

    public static ResponseEntity<ResponseDto> noPermission(){
        ResponseDto result = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }
}
