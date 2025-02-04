package com.example.AIChat.dto.object;

import java.util.ArrayList;
import java.util.List;

import com.example.AIChat.entity.ChatRoomListViewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomListItem {
    
    private int chatRoomId;
    private String chatRoomName;
    private String userLoginId;

    public ChatRoomListItem(ChatRoomListViewEntity chatListViewEntity){
        this.chatRoomId = chatListViewEntity.getChatRoomId();
        this.chatRoomName = chatListViewEntity.getChatRoomName();
        this.userLoginId = chatListViewEntity.getUserLoginId();
    }

    public static List<ChatRoomListItem> getList(List<ChatRoomListViewEntity> chatListViewEntities){
        List<ChatRoomListItem> list = new ArrayList<>();

        for(ChatRoomListViewEntity chatListViewEntity: chatListViewEntities){
            ChatRoomListItem chatListItem = new ChatRoomListItem(chatListViewEntity);
            list.add(chatListItem);
        }

        return list;
    }
}
