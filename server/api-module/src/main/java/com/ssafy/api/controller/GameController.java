package com.ssafy.api.controller;

import com.ssafy.api.dto.Message;
import com.ssafy.api.service.GameService;
import com.ssafy.core.entity.GameRoom;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"07. 메세지 보내기"})
public class GameController {

    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달
    private final GameService gameService; //게임 서비스

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/message"
    @MessageMapping(value = "/room/message")
    public void message(@RequestBody Message message) {

        System.out.println("message = " + message.toString());
        if (Message.Type.ENTER.equals(message.getType())) {
            gameService.updateUserCount(message.getRoomId(), 1, message.getSender());
            message.setMessage("[알림]" + message.getSender() + "님이 입장하셨습니다");
        } else if (Message.Type.OUT.equals(message.getType())) {
            gameService.updateUserCount(message.getRoomId(), -1, message.getSender());
            message.setMessage("[알림]" + message.getSender() + "님이 퇴장하셨습니다");
        } else if (Message.Type.START.equals(message.getType())) {
            message.setMessage("[알림] 게임을 시작합니다");
        } else if (Message.Type.END.equals(message.getType())) {
            message.setMessage("[알림] 게임을 종료합니다");
        }
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
