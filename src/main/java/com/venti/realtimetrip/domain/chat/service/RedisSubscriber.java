package com.venti.realtimetrip.domain.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.venti.realtimetrip.domain.chat.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;

    private final RedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {

            String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());

            ChatMessageDto chatMessageRequest = objectMapper.readValue(publishMessage, ChatMessageDto.class);

            if (chatMessageRequest.getMessageType().equals(ChatMessageDto.MessageType.TALK)) {
                ChatMessageDto chatMessageResponse = new ChatMessageDto();
                chatMessageResponse.setMessageType(chatMessageRequest.getMessageType());
                chatMessageResponse.setRoomId(chatMessageRequest.getRoomId());
                chatMessageResponse.setSender(chatMessageRequest.getSender());
                chatMessageResponse.setMessage(chatMessageRequest.getMessage());
                chatMessageResponse.setTime(chatMessageRequest.getTime());

                messagingTemplate.convertAndSend("/sub/chat/room/" + chatMessageRequest.getRoomId(), chatMessageResponse);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
