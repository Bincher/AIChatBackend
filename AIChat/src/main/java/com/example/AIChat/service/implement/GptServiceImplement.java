package com.example.AIChat.service.implement;

import com.example.AIChat.dto.request.gpt.GptRequestDto;
import com.example.AIChat.dto.response.gpt.GptForFactCheckResponseDto;
import com.example.AIChat.dto.response.gpt.GptForOrthographyResponseDto;
import com.example.AIChat.dto.response.gpt.GptResponseDto;
import com.example.AIChat.service.GptService;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GptServiceImplement implements GptService{

    @Value("${gpt.model}")
    private String model;

    @Value("${gpt.model.4}")
    private String model4;

    @Value("${gpt.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public String getChatResponse(String prompt) {
        // 비즈니스 로직: GPT 요청 생성 및 처리
        GptRequestDto request = new GptRequestDto(
                model, prompt, 1, 256, 1, 2, 2);

        GptResponseDto gptResponse = restTemplate.postForObject(
                apiUrl,
                request,
                GptResponseDto.class
        );

        // GPT 응답 반환
        return gptResponse.getChoices().get(0).getMessage().getContent();
    }

    @Override
    public ResponseEntity<GptForOrthographyResponseDto> gptForOrthography(String prompt) {
        // 1. GPT 요청 데이터 생성
        GptRequestDto requestDto = new GptRequestDto(
            model,
            prompt,
            0,       // temperature: 창의성 낮게 설정 (정확성 우선)
            256,     // maxTokens: 응답 길이 제한
            1,       // topP: 확률 분포 기반 샘플링
            0,       // frequencyPenalty: 반복 억제 없음
            0        // presencePenalty: 새로운 주제 유도 없음
        );

        // 2. GPT API 호출
        GptResponseDto responseDto = restTemplate.postForObject(
            apiUrl,
            requestDto,
            GptResponseDto.class
        );

        // 3. 응답 데이터 처리
        if (responseDto == null || responseDto.getChoices().isEmpty()) {
            return ResponseEntity.status(500).body(new GptForOrthographyResponseDto(
                "GPT 응답이 비어있습니다.", prompt
            ));
        }

        // GPT가 반환한 교정된 텍스트 추출
        String correctedText = responseDto.getChoices().get(0).getMessage().getContent();

        // 4. 결과 DTO 생성 및 반환
        return GptForOrthographyResponseDto.success(correctedText, prompt);
    }

    @Override
    public ResponseEntity<GptForFactCheckResponseDto> gptForFactCheck(String prompt) {
        // 1. GPT 요청 데이터 생성
        GptRequestDto requestDto = new GptRequestDto(
            model4,
            prompt,
            0,       // temperature: 창의성 낮게 설정 (정확성 우선)
            256,     // maxTokens: 응답 길이 제한
            1,       // topP: 확률 분포 기반 샘플링
            0,       // frequencyPenalty: 반복 억제 없음
            0        // presencePenalty: 새로운 주제 유도 없음
        );

        // 2. GPT API 호출
        GptResponseDto responseDto = restTemplate.postForObject(
            apiUrl,
            requestDto,
            GptResponseDto.class
        );

        // 3. 응답 데이터 처리
        if (responseDto == null || responseDto.getChoices().isEmpty()) {
            return ResponseEntity.status(500).body(new GptForFactCheckResponseDto(
                "GPT 응답이 비어있습니다.", prompt
            ));
        }

        // GPT가 반환한 교정된 텍스트 추출
        String correctedText = responseDto.getChoices().get(0).getMessage().getContent();

        // 4. 결과 DTO 생성 및 반환
        return GptForFactCheckResponseDto.success(correctedText, prompt);
    }
    
}
