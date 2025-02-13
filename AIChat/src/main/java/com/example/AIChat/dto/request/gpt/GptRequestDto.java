package com.example.AIChat.dto.request.gpt;

import java.util.ArrayList;
import java.util.List;

import com.example.AIChat.dto.object.Message;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GptRequestDto {
    private String model;
    private List<Message> messages;
    private int temperature;
    private int maxTokens;
    private int topP;
    private int frequencyPenalty;
    private int presencePenalty;

    public GptRequestDto(String model
            , String prompt
            , int temperature
            , int maxTokens
            , int topP
            , int frequencyPenalty
            , int presencePenalty) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
        this.temperature = temperature;
        this.maxTokens = maxTokens;
        this.topP=topP;
        this.frequencyPenalty=frequencyPenalty;
        this.presencePenalty = presencePenalty;

    }
}