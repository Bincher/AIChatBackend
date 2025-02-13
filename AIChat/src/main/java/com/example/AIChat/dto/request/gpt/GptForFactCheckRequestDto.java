package com.example.AIChat.dto.request.gpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GptForFactCheckRequestDto {
    private String prompt; // 사용자가 입력한 채팅 내용 (검사 대상)
}
