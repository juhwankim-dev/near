package com.ssafy.api.controller;

import com.ssafy.api.dto.res.FingerContentResDTO;
import com.ssafy.api.service.FingerService;
import com.ssafy.api.service.common.ListResult;
import com.ssafy.api.service.common.ResponseService;
import com.ssafy.api.service.common.SingleResult;
import com.ssafy.core.entity.Fingercontent;
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

@Api(tags = {"04. 지문자"})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/finger")
@CrossOrigin(origins = "*")
public class FingerController {
    private final FingerService fingerService;
    private final ResponseService responseService;


    @ApiOperation(value = "지문자 목록", notes = "지문자의 목록을 불러온다")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ListResult<FingerContentResDTO> FingerList() throws Exception {

        List<Fingercontent> fingerList = fingerService.fingerList();

        if (fingerList == null) {
            throw new ApiMessageException("지문자 목록이 없습니다");
        }
        List<FingerContentResDTO> result = new ArrayList<>();
        for (Fingercontent fingercontent : fingerList) {
            result.add(new FingerContentResDTO(
                    fingercontent.getFingercotent_key(),
                    fingercontent.getName(),
                    fingercontent.getCategory(),
                    fingercontent.getImage_path(),
                    fingercontent.getExplanation()
            ));
        }
        return responseService.getListResult(result, "성공");
    }

}
