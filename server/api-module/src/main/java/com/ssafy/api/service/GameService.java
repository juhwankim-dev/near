package com.ssafy.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.core.entity.GameRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    public GameRoom createRoom(String name){
        //TODO : roomId == Summoner MatchId + blue/red Team code + Summoner Name
        String roomId = name;
        GameRoom result = GameRoom.builder().roomId(roomId).build();
        gameRooms.put(roomId, result);

        return result;
    }


    public <T> void sendMessage(WebSocketSession session, T message){
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        }catch (IOException e){
            log.error(e.getMessage(), e);
        }
    }


}
