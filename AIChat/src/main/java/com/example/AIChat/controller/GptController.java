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
    public ResponseEntity<? super GptForOrthographyResponseDto> gptForOrthography(
        @RequestBody GptForOrthographyRequestDto requestBody
    ) {
        return gptService.gptForOrthography(requestBody);
    }
    
    @PostMapping("/fact-check")
    public ResponseEntity<? super GptForFactCheckResponseDto> gptForFactCheck(
        @RequestBody GptForFactCheckRequestDto requestBody
    ) {
        return gptService.gptForFactCheck(requestBody);
    }

    @PostMapping("/recommend-text")
    public ResponseEntity<? super GptForRecommendTextResponseDto> getForRecommendText(
        @RequestBody GptForRecommendTextRequestDto requestBody
    ) {
        return gptService.gptForRecommendText(requestBody);
    }

    @PostMapping("/summary")
    public ResponseEntity<? super GptForSummaryResponseDto> getForSummary(
        @RequestBody GptForSummaryRequestDto requestBody
    ) {
        return gptService.gptForSummary(requestBody);
    }
}
