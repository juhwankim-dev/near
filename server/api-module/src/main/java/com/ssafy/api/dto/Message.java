package com.ssafy.api.dto;

import lombok.*;

@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    //입장, 나감, 이야기, 게임시작, 게임종료
    public enum Type {
        ENTER, OUT, TALK, START, END
    }

    private Type type; // 메시지 타입
    private String roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지
}
