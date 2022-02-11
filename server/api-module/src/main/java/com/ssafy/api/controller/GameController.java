package com.ssafy.api.controller;

import com.ssafy.api.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GameController {

    private SimpMessageSendingOperations messageSendingOperations; //특정 Broker로 메세지를 전달

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping("/room/message")
    public void message(Message message) {
        if (Message.Type.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하셨습니다");
        }
        messageSendingOperations.convertAndSend("/sub/game/room/" + message.getRoomId() + message);
    }
}
