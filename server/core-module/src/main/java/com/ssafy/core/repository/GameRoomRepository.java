package com.ssafy.core.repository;

import com.ssafy.core.entity.GameRoom;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class GameRoomRepository {

    private Map<String, GameRoom> gameRoomMap;

    @PostConstruct
    private void init() {
        gameRoomMap = new LinkedHashMap<>();
    }

    public List<GameRoom> findAllRoom() {
        List<GameRoom> gameRooms = new ArrayList<>(gameRoomMap.values());
        //채팅방 생성 순서 최근 순으로 반환
        Collections.reverse(gameRooms);
        return gameRooms;
    }

    public GameRoom findByRoomId(String roomId) {
        return gameRoomMap.get(roomId);
    }

    public GameRoom createGameRoom(String name) {
        GameRoom room = GameRoom.builder().roomId(name).build();
        gameRoomMap.put(room.getRoomId(), room);
        return room;
    }
}
