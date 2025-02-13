package com.example.AIChat.dto.request.gpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GptForSummaryRequestDto {
    private String roomId;
}
