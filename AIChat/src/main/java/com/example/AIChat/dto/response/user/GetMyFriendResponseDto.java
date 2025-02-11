package com.example.AIChat.dto.response.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.AIChat.common.ResponseCode;
import com.example.AIChat.common.ResponseMessage;
import com.example.AIChat.dto.object.FriendListItem;
import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetMyFriendResponseDto extends ResponseDto{
    private List<FriendListItem> friends;

    public GetMyFriendResponseDto(List<FriendListItem> friends) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.friends = friends; 
    }

    public static ResponseEntity<GetMyFriendResponseDto> success(List<UserEntity> userEntities) {
        List<FriendListItem> friends = FriendListItem.getList(userEntities);
        GetMyFriendResponseDto result = new GetMyFriendResponseDto(friends);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
