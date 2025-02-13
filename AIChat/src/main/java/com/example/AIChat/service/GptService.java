package com.example.AIChat.service;

import org.springframework.http.ResponseEntity;

import com.example.AIChat.dto.request.gpt.GptForFactCheckRequestDto;
import com.example.AIChat.dto.request.gpt.GptForOrthographyRequestDto;
import com.example.AIChat.dto.request.gpt.GptForRecommendTextRequestDto;
import com.example.AIChat.dto.request.gpt.GptForSummaryRequestDto;
import com.example.AIChat.dto.response.gpt.GptForFactCheckResponseDto;
import com.example.AIChat.dto.response.gpt.GptForOrthographyResponseDto;
import com.example.AIChat.dto.response.gpt.GptForRecommendTextResponseDto;
import com.example.AIChat.dto.response.gpt.GptForSummaryResponseDto;

public interface GptService {

    public String getChatResponse(String prompt);
    public ResponseEntity<? super GptForOrthographyResponseDto> gptForOrthography(GptForOrthographyRequestDto dto);
    public ResponseEntity<? super GptForFactCheckResponseDto> gptForFactCheck(GptForFactCheckRequestDto dto);
    public ResponseEntity<? super GptForRecommendTextResponseDto> gptForRecommendText(GptForRecommendTextRequestDto dto);
    public ResponseEntity<? super GptForSummaryResponseDto> gptForSummary(GptForSummaryRequestDto dto);
}
