package com.example.AIChat.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetUserListRequestDto {
    @NotBlank
    private String nickname;
}
