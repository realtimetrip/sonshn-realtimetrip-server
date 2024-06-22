package com.venti.realtimetrip.domain.chat.service;

import com.venti.realtimetrip.domain.chat.dto.ChatRoomDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final Map<String, ChatRoomDto> chatRoomDtoMap = new LinkedHashMap<>();

    public List<ChatRoomDto> findAllRooms() {
        List<ChatRoomDto> chatRooms = new ArrayList<>(chatRoomDtoMap.values());
        Collections.reverse(chatRooms);

        return chatRooms;
    }

    public ChatRoomDto findRoomById(String roomId) {
        return chatRoomDtoMap.get(roomId);
    }

    public ChatRoomDto createChatRoom(String roomName) {
        ChatRoomDto chatRoomDto = new ChatRoomDto().createChatRoom(roomName);
        chatRoomDtoMap.put(chatRoomDto.getRoomId(), chatRoomDto);

        return chatRoomDto;
    }

    public void increaseUserCount(String roomId) {
        ChatRoomDto roomDto = chatRoomDtoMap.get(roomId);
        roomDto.setUserCount(roomDto.getUserCount() + 1);
    }

    public void decreaseUserCount(String roomId) {
        ChatRoomDto roomDto = chatRoomDtoMap.get(roomId);
        roomDto.setUserCount(roomDto.getUserCount() - 1);
    }

    public String addUser(String roomId, String userName) {
        ChatRoomDto roomDto = chatRoomDtoMap.get(roomId);

        String userUUID = UUID.randomUUID().toString();
        roomDto.getUserList().put(userUUID, userName);

        return userUUID;
    }

    public void deleteUser(String roomId, String userUUID) {
        ChatRoomDto roomDto = chatRoomDtoMap.get(roomId);

        roomDto.getUserList().remove(userUUID);
    }

    public String getUserName(String roomId, String userUUID) {
        ChatRoomDto roomDto = chatRoomDtoMap.get(roomId);

        return roomDto.getUserList().get(userUUID);
    }

    public ArrayList<String> getUserListByRoomId(String roomId) {
        ChatRoomDto roomDto = chatRoomDtoMap.get(roomId);

        ArrayList<String> list = new ArrayList<>();
        roomDto.getUserList().forEach((key, value) -> list.add(value));

        return list;
    }

}

