package com.example.AIChat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.AIChat.dto.request.gpt.GptForFactCheckRequestDto;
import com.example.AIChat.dto.request.gpt.GptForOrthographyRequestDto;
import com.example.AIChat.dto.request.gpt.GptForRecommendTextRequestDto;
import com.example.AIChat.dto.request.gpt.GptForSummaryRequestDto;
import com.example.AIChat.dto.response.gpt.GptForFactCheckResponseDto;
import com.example.AIChat.dto.response.gpt.GptForOrthographyResponseDto;
import com.example.AIChat.dto.response.gpt.GptForRecommendTextResponseDto;
import com.example.AIChat.dto.response.gpt.GptForSummaryResponseDto;
import com.example.AIChat.service.GptService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/gpt")
public class GptController {

    private final GptService gptService;

    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt) {
        return gptService.getChatResponse(prompt);
    }

    @PostMapping("/orthography")
    public ResponseEntity<GptForOrthographyResponseDto> gptForOrthography(
        @RequestBody GptForOrthographyRequestDto requestDto
    ) {
        return gptService.gptForOrthography(requestDto.getPrompt());
    }
    
    @PostMapping("/fact-check")
    public ResponseEntity<GptForFactCheckResponseDto> gptForFactCheck(
        @RequestBody GptForFactCheckRequestDto requestDto
    ) {
        return gptService.gptForFactCheck(requestDto.getPrompt());
    }

    @PostMapping("/recommend-text")
    public ResponseEntity<GptForRecommendTextResponseDto> getForRecommendText(
        @RequestBody GptForRecommendTextRequestDto requestDto
    ) {
        return gptService.gptForRecommendText(requestDto.getRoomId());
    }

    @PostMapping("/summary")
    public ResponseEntity<GptForSummaryResponseDto> getForSummary(
        @RequestBody GptForSummaryRequestDto requestDto
    ) {
        return gptService.gptForSummary(requestDto.getRoomId());
    }
}
