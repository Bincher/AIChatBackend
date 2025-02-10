package com.example.AIChat.dto.response.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.AIChat.common.ResponseCode;
import com.example.AIChat.common.ResponseMessage;
import com.example.AIChat.dto.object.FriendDto;
import com.example.AIChat.dto.response.ResponseDto;
import com.example.AIChat.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetMyFriendResponseDto extends ResponseDto{
    private List<FriendDto> friends;

    public GetMyFriendResponseDto(List<FriendDto> friends) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS); // 부모 클래스의 생성자 호출
        this.friends = friends; // friends 필드 초기화
    }

    public static ResponseEntity<GetMyFriendResponseDto> success(List<UserEntity> userEntities) {
        // FriendDto로 변환
        List<FriendDto> friends = FriendDto.getList(userEntities);
        // 새로운 생성자를 사용하여 객체 생성
        GetMyFriendResponseDto result = new GetMyFriendResponseDto(friends);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
