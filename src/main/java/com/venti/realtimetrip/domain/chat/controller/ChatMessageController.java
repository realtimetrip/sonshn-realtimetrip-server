package com.venti.realtimetrip.domain.chat.controller;

import com.venti.realtimetrip.domain.chat.dto.ChatMessageDto;
import com.venti.realtimetrip.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatMessageController {

    private final ChatService chatService;

    private final SimpMessageSendingOperations template;

    private static final String CHAT_QUEUE_NAME = "chat.queue";
    private static final String CHAT_EXCHANGE_NAME = "chat.exchange";

    /**
     * 채팅방 입장
     * @param message
     * @param headerAccessor
     */
    @MessageMapping("/enter-user")
    public void enterUser(@Payload ChatMessageDto message, SimpMessageHeaderAccessor headerAccessor) {
        chatService.increaseUserCount(message.getRoomId());
        String userUUID = chatService.addUser(message.getRoomId(), message.getSender());

        headerAccessor.getSessionAttributes().put("userUUID", userUUID);
        headerAccessor.getSessionAttributes().put("roomId", message.getRoomId());

        message.setMessage(message.getSender() + " 님 입장!!");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    /**
     * 메시지 발송
     * @param message
     */
    @MessageMapping("/send-message")
    public void sendMessage(@Payload ChatMessageDto message) {
        message.setMessage(message.getMessage());
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

}
