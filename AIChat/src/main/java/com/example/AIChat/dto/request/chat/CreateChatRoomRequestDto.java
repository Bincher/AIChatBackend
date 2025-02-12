package com.example.AIChat.dto.request.chat;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateChatRoomRequestDto {
    @NotBlank
    private String roomName;
    @NotEmpty
    private List<String> userNicknames;
}
