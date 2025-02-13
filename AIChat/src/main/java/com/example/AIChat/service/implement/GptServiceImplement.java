package com.example.AIChat.service.implement;

import com.example.AIChat.common.ResponseCode;
import com.example.AIChat.dto.object.Message;
import com.example.AIChat.dto.request.gpt.GptRequestDto;
import com.example.AIChat.dto.response.gpt.GptForFactCheckResponseDto;
import com.example.AIChat.dto.response.gpt.GptForOrthographyResponseDto;
import com.example.AIChat.dto.response.gpt.GptForRecommendTextResponseDto;
import com.example.AIChat.dto.response.gpt.GptResponseDto;
import com.example.AIChat.entity.MessageEntity;
import com.example.AIChat.repository.MessageRepository;
import com.example.AIChat.service.GptService;


import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private final MessageRepository messageRepository;

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
    
    public ResponseEntity<GptForRecommendTextResponseDto> gptForRecommendText(String roomId) {
        // 1. MongoDB에서 채팅 내역 가져오기
        List<MessageEntity> chatHistory = messageRepository.findAllByRoomId(roomId);
        if (chatHistory.isEmpty()) {
            return ResponseEntity.badRequest().body(new GptForRecommendTextResponseDto(
                ResponseCode.VALIDATION_FAILED,
                "No chat history found for the given roomId.",
                null,
                roomId
            ));
        }

        // 2. 채팅 내역을 GPT API 요청 형식으로 변환
        String chatHistoryString = chatHistory.stream()
            .map(message -> message.getSender() + ": " + message.getContent())
            .collect(Collectors.joining("\n"));

        // System.out.println(chatHistoryString);

        // 3. GPT 요청 데이터 생성
        List<Message> messages = new ArrayList<>();
        // 시스템 메시지 추가: 한국어 응답 요청
        messages.add(new Message("system", "모든 응답을 한국어로 제공해주세요. 다음 대화내역을 보고 상황에 맞는 답변을 조언해주세요."));
        // 사용자 대화 기록 추가
        messages.add(new Message("user", chatHistoryString));
                
        GptRequestDto requestDto = new GptRequestDto(
            model,
            chatHistoryString,
            1,   // temperature: 적당한 창의성
            256,   // maxTokens: 응답 길이 제한
            1,     // topP: 확률 분포 기반 샘플링
            0,     // frequencyPenalty: 반복 억제 없음
            0      // presencePenalty: 새로운 주제 유도 없음
        );

        // 3. GPT API 호출
        GptResponseDto responseDto = restTemplate.postForObject(
            apiUrl,
            requestDto,
            GptResponseDto.class
        );

        // 4. 응답 데이터 처리
        if (responseDto == null || responseDto.getChoices().isEmpty()) {
            return ResponseEntity.status(500).body(new GptForRecommendTextResponseDto(
                ResponseCode.DATABASE_ERROR,
                "Failed to get a response from GPT.",
                null,
                roomId
            ));
        }

        String recommendedText = responseDto.getChoices().get(0).getMessage().getContent();

        // 5. 결과 반환
        return GptForRecommendTextResponseDto.success(recommendedText, roomId);
    }
}
