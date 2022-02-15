package com.ssafy.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.core.entity.GameRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameService {

    private final ObjectMapper objectMapper;
    private Map<String, GameRoom> gameRooms;

    @PostConstruct
    private void init() {
        gameRooms = new LinkedHashMap<>();
    }

    public List<GameRoom> findAllRoom(){
        return new ArrayList<>(gameRooms.values());
    }

    public GameRoom findById(String roomId){
        return gameRooms.get(roomId);
    }

    public GameRoom createRoom(String name, String host){
        GameRoom gameRoom = GameRoom.create(name, host);
        gameRooms.put(gameRoom.getRoomId(), gameRoom);
        return gameRoom;
    }

    @Transactional(readOnly = false)
    public void deleteGameRoom(String roomId){
        gameRooms.remove(roomId);
    }

    public GameRoom updateUserCount(String roomId, int count, String userNickName){
        GameRoom gameRoom = gameRooms.get(roomId);
        List<String> userList = gameRoom.getUserList();
        if (count == 1){
            userList.add(userNickName);
        }else {
            userList.remove(userNickName);
        }
        gameRoom.setUserCount(gameRoom.getUserCount() + count);
        gameRoom.setUserList(userList);
        gameRooms.replace(roomId, gameRoom);

        return gameRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message){
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        }catch (IOException e){
            log.error(e.getMessage(), e);
        }
    }


}
