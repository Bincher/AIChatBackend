package com.example.AIChat.dto.response.gpt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.AIChat.common.ResponseCode;
import com.example.AIChat.common.ResponseMessage;
import com.example.AIChat.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GptForSummaryResponseDto extends ResponseDto {
    private String recommendedText; // GPT가 추천한 답장
    private String roomId;          // 요청된 채팅방 ID

    public GptForSummaryResponseDto(String code, String message, String summaryText, String roomId) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.recommendedText = summaryText;
        this.roomId = roomId;
    }

    public static ResponseEntity<GptForSummaryResponseDto> success(String recommendedText, String roomId) {
        GptForSummaryResponseDto result = new GptForSummaryResponseDto(
            ResponseCode.SUCCESS,
            ResponseMessage.SUCCESS,
            recommendedText,
            roomId
        );
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
