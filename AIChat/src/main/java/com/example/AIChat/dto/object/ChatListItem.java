package com.example.AIChat.dto.object;

import java.util.ArrayList;
import java.util.List;

import com.example.AIChat.entity.ChatListViewEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatListItem {
    
    private int chatRoomId;
    private String chatRoomName;
    private int userId;
    private String userNickname;
    private String userProfileImage;

    public ChatListItem(ChatListViewEntity chatListViewEntity){
        this.chatRoomId = chatListViewEntity.getChatRoomId();
        this.chatRoomName = chatListViewEntity.getChatRoomName();
        this.userId = chatListViewEntity.getUserId();
        this.userNickname = chatListViewEntity.getUserNickname();
        this.userProfileImage = chatListViewEntity.getUserProfileImage();
    }

    public static List<ChatListItem> getList(List<ChatListViewEntity> chatListViewEntities){
        List<ChatListItem> list = new ArrayList<>();

        for(ChatListViewEntity chatListViewEntity: chatListViewEntities){
            ChatListItem chatListItem = new ChatListItem(chatListViewEntity);
            list.add(chatListItem);
        }

        return list;
    }
}
