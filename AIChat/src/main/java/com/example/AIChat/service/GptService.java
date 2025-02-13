package com.example.AIChat.service;

import org.springframework.http.ResponseEntity;

import com.example.AIChat.dto.response.gpt.GptForOrthographyResponseDto;

public interface GptService {

    public String getChatResponse(String prompt);
    public ResponseEntity<GptForOrthographyResponseDto> gptForOrthography(String prompt);
}
