package com.example.AIChat.dto.object;

import java.util.ArrayList;
import java.util.List;

import com.example.AIChat.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendListItem {
    private String nickname;
    private String profileImage;

    public FriendListItem(UserEntity user) {
        this.nickname = user.getNickname();
        this.profileImage = user.getProfileImage();
    }

    // 정적 메서드: UserEntity 리스트를 FriendDto 리스트로 변환
    public static List<FriendListItem> getList(List<UserEntity> userEntities) {
        List<FriendListItem> list = new ArrayList<>();
        for (UserEntity user : userEntities) {
            list.add(new FriendListItem(user));
        }
        return list;
    }
}