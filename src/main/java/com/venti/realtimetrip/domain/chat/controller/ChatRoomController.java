package com.venti.realtimetrip.domain.chat.controller;

import com.venti.realtimetrip.domain.chat.dto.ChatRoomDto;
import com.venti.realtimetrip.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chatroom")
public class ChatRoomController {

    private final ChatService chatService;

    /**
     * 채팅 메인 화면
     * @return 전체 채팅방 리스트
     */
    @GetMapping("/")
    public ResponseEntity<List<ChatRoomDto>> goChatRoom() {
        List<ChatRoomDto> chatRooms = chatService.findAllRooms();

        return ResponseEntity.ok(chatRooms);
    }

    /**
     * 채팅방 생성
     * @param roomName 새로운 채팅방 이름
     * @return 생성된 채팅방의 UUID
     */
    @PostMapping("/create-room")
    public ResponseEntity<String> createChatRoom(@RequestParam("roomName") String roomName) {
        ChatRoomDto chatRoomDto = chatService.createChatRoom(roomName);

        return ResponseEntity.ok(chatRoomDto.getRoomId());
    }

    /**
     * 채팅방에 참여 중인 유저 리스트 반환
     * @param roomId 채팅방 아이디
     * @return 채팅방에 참여 중인 유저 리스트
     */
    @GetMapping("/user-list")
    public ArrayList<String> getUserListByRoomId(String roomId) {
        return chatService.getUserListByRoomId(roomId);
    }

}
