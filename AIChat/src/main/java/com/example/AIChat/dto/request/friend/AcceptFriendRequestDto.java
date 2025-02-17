package com.example.AIChat.dto.request.friend;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AcceptFriendRequestDto {
    @NotBlank
    private String nickname;
    @NotNull
    private boolean friendAccept;
}
