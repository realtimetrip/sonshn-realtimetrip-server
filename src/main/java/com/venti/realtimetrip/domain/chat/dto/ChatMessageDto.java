package com.venti.realtimetrip.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
    public enum MessageType {
        ENTER, TALK, LEAVE;
    }

    private MessageType messageType;
    private String roomId;
    private String sender;
    private String message;
    private String time;
}
