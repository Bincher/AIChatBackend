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
public class GetInviteFriendResponseDto extends ResponseDto{

    private List<FriendListItem> friends;

    private GetInviteFriendResponseDto(List<FriendListItem> friends){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.friends = friends; 
    }

    public static ResponseEntity<GetInviteFriendResponseDto> success(List<UserEntity> userEntities){
        List<FriendListItem> friends = FriendListItem.getList(userEntities);
        GetInviteFriendResponseDto result = new GetInviteFriendResponseDto(friends);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
