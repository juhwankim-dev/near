package com.ssafy.api.controller;

import com.ssafy.api.dto.res.FingerContentResDTO;
import com.ssafy.api.dto.res.HandContentResDTO;
import com.ssafy.api.service.HandService;
import com.ssafy.api.service.common.ListResult;
import com.ssafy.api.service.common.ResponseService;
import com.ssafy.core.entity.Fingercontent;
import com.ssafy.core.entity.Handcontent;
import com.ssafy.core.exception.ApiMessageException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = {"05. 수어"})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/hand")
@CrossOrigin(origins = "*")
public class HandController {
    private final HandService handService;
    private final PasswordEncoder passwordEncoder;
    private final ResponseService responseService;


    @ApiOperation(value = "수어 목록", notes = "수어의 목록을 불러온다")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ListResult<HandContentResDTO> HandList() throws Exception {

        List<Handcontent> handList = handService.handList();


        if (handList == null) {
            throw new ApiMessageException("수어의 목록이 없습니다");
        }
        List<HandContentResDTO> result = new ArrayList<>();
        for (Handcontent handcontent : handList) {
            result.add(new HandContentResDTO().builder()
                    .name(handcontent.getName())
                    .explanation(handcontent.getExplanation())
                    .handcontent_key(handcontent.getHandcontent_key())
                    .image_path(handcontent.getImage_path())
                    .mean(handcontent.getMean())
                    .video_path(handcontent.getVideo_path()).build());
        }

        return responseService.getListResult(result, "성공");
    }



}
