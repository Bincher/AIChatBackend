package com.example.AIChat.dto.response.gpt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.AIChat.common.ResponseCode;
import com.example.AIChat.common.ResponseMessage;
import com.example.AIChat.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class GptForOrthographyResponseDto extends ResponseDto{
    private String correctedText; // 교정된 텍스트
    private String originalText;  // 원본 텍스트

    public GptForOrthographyResponseDto(String correctedText, String originalText) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.correctedText = correctedText;
        this.originalText = originalText;
    }

    public static ResponseEntity<GptForOrthographyResponseDto> success(String correctedText, String originalText){
        GptForOrthographyResponseDto result = new GptForOrthographyResponseDto(correctedText, originalText);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistPrompt(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_PROMPT, ResponseMessage.NOT_EXISTED_PROMPT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistResult(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_RESULT, ResponseMessage.NOT_EXISTED_RESULT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
