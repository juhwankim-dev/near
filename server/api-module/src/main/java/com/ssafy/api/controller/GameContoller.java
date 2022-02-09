package com.ssafy.api.controller;

import com.ssafy.api.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GameContoller {

    private SimpMessageSendingOperations messageSendingOperations;

    @MessageMapping("/room/message")
    public void message(Message message) {
        if (Message.MessageType.ENTER.equals(message.getMessageType())) {
            message.setMessage(message.getSender() + "님이 입장하셨습니다");
        }
        messageSendingOperations.convertAndSend("/sub/game/room/" + message);
    }
}
