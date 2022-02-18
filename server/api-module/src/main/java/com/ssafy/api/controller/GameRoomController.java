package com.ssafy.api.controller;

import com.ssafy.api.service.GameService;
import com.ssafy.api.service.common.ListResult;
import com.ssafy.api.service.common.ResponseService;
import com.ssafy.api.service.common.SingleResult;
import com.ssafy.core.entity.GameRoom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
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
    private final ResponseService responseService;

    // 게임방 리스트 화면
    @GetMapping("/room")
    public String room(Model model) {
        return "/api/game/room";
    }

    // 모든 채팅방 목록
    @ApiOperation(value = "채팅방 목록", notes = "모든 채팅방 목록을 반환한다")
    @GetMapping("/rooms")
    public ListResult<GameRoom> rooms() {
        return responseService.getListResult(gameService.findAllRoom());
    }

    //게임방 생성
    @ApiOperation(value = "게임방 생성", notes = "만들고 싶은 게임방을 생성한다")
    @PostMapping("/room")
    @ResponseBody
    public GameRoom createRoom(@ApiParam(value = "방 이름", required = true) @RequestParam String name, @ApiParam(value = "방장 닉네임", required = true) @RequestParam String host) {
        return gameService.createRoom(name, host);
    }

    // 게임방 입장 화면
    @ApiOperation(value = "방 입장", notes = "room ID를 통해서 방에 입장합니다")
    @GetMapping("/room/enter/{roomId}")
    public String roomEnter(Model model, @ApiParam(value = "방 ID", required = true) @PathVariable String roomId) {

        model.addAttribute("roomId", roomId);
        return "game/roomEnter";
    }

    // 특정 게임방 조회
    @ApiOperation(value = "방 조회", notes = "room ID를 통해서 방을 조회합니다")
    @GetMapping("/room/{roomId}")
    public SingleResult<GameRoom> roomInfo(
            @ApiParam(value = "방 ID", required = true) @PathVariable String roomId) {
        return responseService.getSingleResult(gameService.findById(roomId));
    }

    // 게임방 삭제
    @ResponseBody
    @ApiOperation(value = "방 삭제", notes = "room ID를 이용해서 방을 삭제합니다")
    @DeleteMapping("/room/{roomId}")
    public SingleResult<Boolean> deleteRoom(@ApiParam(value = "방 ID", required = true) @PathVariable String roomId) {
        gameService.deleteGameRoom(roomId);
        return responseService.getSingleResult(true);
    }

    // 게임방 유저수 Update
    @ApiOperation(value = "유저 수 변경", notes = "room ID와 count를 이용해서 방의 유저수를 변경 합니다")
    @PutMapping("/room")
    public SingleResult<GameRoom> updateUserCount(@ApiParam(value = "방 ID", required = true) @RequestParam String roomId, @ApiParam(value = "수정할 유저 수", required = true) @RequestParam String count,
                                                  @ApiParam(value = "입장할 유저 닉네임", required = true) @RequestParam String userNickname) {
        GameRoom gameRoom = gameService.updateUserCount(roomId, Integer.parseInt(count), userNickname);
        return responseService.getSingleResult(gameRoom);
    }

}
