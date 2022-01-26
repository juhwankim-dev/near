package com.ssafy.api.controller;

import com.ssafy.api.dto.res.FingerDetailResDTO;
import com.ssafy.api.dto.res.FingerListResDTO;
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
import java.util.Optional;

@Api(tags = {"04. 지문자"})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/finger")
@CrossOrigin(origins = "*")
public class FingerController {
    private final FingerService fingerService;
    private final PasswordEncoder passwordEncoder;
    private final ResponseService responseService;


    @ApiOperation(value = "지문자 목록", notes = "지문자의 목록을 불러온다")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ListResult<FingerListResDTO> FingerList() throws Exception {

        List<Fingercontent> fingerList = fingerService.fingerList();

        if (fingerList == null) {
            throw new ApiMessageException("지문자 목록이 없습니다");
        }

        List<FingerListResDTO> result = new ArrayList<>();

        for (Fingercontent fingercontent : fingerList) {
            result.add(new FingerListResDTO(fingercontent.getFingercotent_key(), fingercontent.getName()));
        }

        return responseService.getListResult(result, "성공");
    }

    @ApiOperation(value = "지문자 상세정보", notes = "해당 지문자의 상세정보를 불러온다")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SingleResult<FingerDetailResDTO> FingerDetail(@PathVariable String id) throws Exception {

        Fingercontent fingercontent = fingerService.fingerDetail(Long.parseLong(id));

        FingerDetailResDTO result =
                new FingerDetailResDTO(fingercontent.getFingercotent_key(), fingercontent.getName(), fingercontent.getCategory(), fingercontent.getImage_path(), fingercontent.getExplanation());


        return responseService.getSingleResult(result);
    }


}
