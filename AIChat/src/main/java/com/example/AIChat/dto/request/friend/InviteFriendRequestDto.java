package com.example.AIChat.dto.request.friend;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InviteFriendRequestDto {
    @NotBlank
    private String nickname;
}
