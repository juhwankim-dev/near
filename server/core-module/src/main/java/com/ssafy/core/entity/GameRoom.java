package com.ssafy.core.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "gameroom",
//        uniqueConstraints = {
//                @UniqueConstraint(
//                        columnNames = {"id"}
//                )
//        }
//)
public class GameRoom {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String roomId;
    private String name;

    public static GameRoom create(String name) {
        GameRoom gameRoom = new GameRoom();
        gameRoom.roomId = UUID.randomUUID().toString();
        gameRoom.name = name;
        return gameRoom;
    }

}
