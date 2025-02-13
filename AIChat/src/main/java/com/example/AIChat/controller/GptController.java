package com.example.AIChat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.AIChat.dto.request.gpt.GptForOrthographyRequestDto;
import com.example.AIChat.dto.response.gpt.GptForOrthographyResponseDto;
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
        // GptService 호출하여 맞춤법 검사 수행
        return gptService.gptForOrthography(requestDto.getPrompt());
    }
}
