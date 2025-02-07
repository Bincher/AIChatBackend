package com.example.AIChat.dto.object;

import java.util.ArrayList;
import java.util.List;

import com.example.AIChat.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserListItem {
    private int id;
    private String nickname;
    private String profileImage;

    public UserListItem(UserEntity userEntity){
        this.id = userEntity.getId();
        this.nickname = userEntity.getNickname();
        this.profileImage = userEntity.getProfileImage();
    }

    public static List<UserListItem> getList(List<UserEntity>userEntities){
        
        List<UserListItem> list = new ArrayList<>();

        for(UserEntity userEntity: userEntities){
            UserListItem userListItem = new UserListItem(userEntity);
            list.add(userListItem);
        }

        return list;
    }
}
