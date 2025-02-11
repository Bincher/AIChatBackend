package com.example.AIChat.dto.response.friend;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.AIChat.common.ResponseCode;
import com.example.AIChat.common.ResponseMessage;
import com.example.AIChat.dto.object.UserListItem;
import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserListResponseDto extends ResponseDto{

    private List<UserListItem> userList;
    
    private GetUserListResponseDto(List<UserEntity> userListEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userList = UserListItem.getList(userListEntities);
    }

    public static ResponseEntity<GetUserListResponseDto> success(List<UserEntity> userListEntities){
        GetUserListResponseDto result = new GetUserListResponseDto(userListEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
