package com.ssafy.api.controller;

import com.ssafy.api.service.GameService;
import com.ssafy.core.entity.GameRoom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = {"06. 게임방"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
public class GameRoomController {

    private final GameService gameService;

    // 게임방 리스트 화면
    @GetMapping("/room")
    public String room(Model model) {
        return "/game/room";
    }

    // 모든 채팅방 목록
    @GetMapping("/rooms")
    public List<GameRoom> rooms() {
        return gameService.findAllRoom();
    }

    //게임방 생성
    @PostMapping("/room")
    @ResponseBody
    public GameRoom createRoom(@RequestParam String name){
        return gameService.createRoom(name);
    }

    // 게임방 입장 화면
    @ApiOperation(value = "방 입장", notes = "room ID를 통해서 방에 입장합니다")
    @GetMapping("/room/eneter/{roomId}")
    public String roomEnter(Model model, @ApiParam(value = "방 ID", required = true) @PathVariable String roomId) {

        model.addAttribute("roomId", roomId);
        return "game/roomEnter";
    }

    // 특정 게임방 조회
    @ApiOperation(value = "방 조회", notes = "room ID를 통해서 방을 조회합니다")
    @GetMapping("/room/{roomId}")
    public GameRoom roomInfo(
            @ApiParam(value = "방 ID", required = true) @PathVariable String roomId) {
        return gameService.findById(roomId);
    }

}
