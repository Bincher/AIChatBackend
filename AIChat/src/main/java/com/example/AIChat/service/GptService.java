package com.example.AIChat.service;

import org.springframework.http.ResponseEntity;

import com.example.AIChat.dto.response.gpt.GptForFactCheckResponseDto;
import com.example.AIChat.dto.response.gpt.GptForOrthographyResponseDto;
import com.example.AIChat.dto.response.gpt.GptForRecommendTextResponseDto;

public interface GptService {

    public String getChatResponse(String prompt);
    public ResponseEntity<GptForOrthographyResponseDto> gptForOrthography(String prompt);
    public ResponseEntity<GptForFactCheckResponseDto> gptForFactCheck(String prompt);
    public ResponseEntity<GptForRecommendTextResponseDto> gptForRecommendText(String roomId);
}
