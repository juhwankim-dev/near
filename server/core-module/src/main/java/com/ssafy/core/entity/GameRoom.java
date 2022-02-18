package com.ssafy.core.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameRoom {

    private String roomId;
    private String name;
    private String host;
    private int userCount; // 채팅방 인원수
    private List<String> userList;

    public static GameRoom create(String name, String host) {
        GameRoom gameRoom = new GameRoom();
        gameRoom.roomId = UUID.randomUUID().toString();
        gameRoom.name = name;
        gameRoom.host = host;
        gameRoom.userCount = 0;
        gameRoom.userList = new ArrayList<>();
        return gameRoom;
    }


}
