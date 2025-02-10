package com.example.AIChat.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchFriendRequestDto {
    @NotBlank
    private String nickname;
    @NotNull
    private boolean friendAccept;
}
