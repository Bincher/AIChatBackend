package com.example.AIChat.dto.object;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ChatRoomWithUsersDto {
    private Integer chatRoomId;
    private String roomName;
    private List<ChatUserDto> users;

}
