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
public class GptForRecommendTextResponseDto extends ResponseDto{
    private String recommendedText; // GPT가 추천한 답장
    private String roomId;          // 요청된 채팅방 ID

    public GptForRecommendTextResponseDto(String code, String message, String recommendedText, String roomId) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.recommendedText = recommendedText;
        this.roomId = roomId;
    }

    public static ResponseEntity<GptForRecommendTextResponseDto> success(String recommendedText, String roomId) {
        GptForRecommendTextResponseDto result = new GptForRecommendTextResponseDto(
            ResponseCode.SUCCESS,
            ResponseMessage.SUCCESS,
            recommendedText,
            roomId
        );
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistResult(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_RESULT, ResponseMessage.NOT_EXISTED_RESULT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
