package com.ssafy.api.controller;

import com.ssafy.api.dto.req.BookmarkReqDTO;
import com.ssafy.api.dto.req.UserIdReqDTO;
import com.ssafy.api.dto.res.FingerContentResDTO;
import com.ssafy.api.dto.res.HandContentResDTO;
import com.ssafy.api.service.HandService;
import com.ssafy.api.service.SignService;
import com.ssafy.api.service.common.CommonResult;
import com.ssafy.api.service.common.ListResult;
import com.ssafy.api.service.common.ResponseService;
import com.ssafy.api.service.common.SingleResult;
import com.ssafy.core.entity.Bookmark;
import com.ssafy.core.entity.Fingercontent;
import com.ssafy.core.entity.Handcontent;
import com.ssafy.core.entity.User;
import com.ssafy.core.exception.ApiMessageException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Book;
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
    private final SignService signService;


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
                    .movement(handcontent.getMovement())
                    .video_path(handcontent.getVideo_path()).build());
        }

        return responseService.getListResult(result, "성공");
    }

    @ApiOperation(value = "북마크 조회", notes = "회원의 북마크 목록을 보여준다")
    @GetMapping(value = "/bookmark/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ListResult<HandContentResDTO> BookmarkList
            (@PathVariable("id") @ApiParam(value = "조회할 유저 번호", required = true) String id) throws Exception {

        List<Handcontent> result = handService.bookmarkList(Long.parseLong(id));

        List<HandContentResDTO> res = new ArrayList<>();
        for (Handcontent handcontent : result) {
            res.add(HandContentResDTO.builder()
                    .handcontent_key(handcontent.getHandcontent_key())
                    .image_path(handcontent.getImage_path())
                    .video_path(handcontent.getVideo_path())
                    .name(handcontent.getName())
                    .explanation(handcontent.getExplanation())
                    .mean(handcontent.getMean())
                    .movement(handcontent.getMovement())
                    .build());
        }


        return responseService.getListResult(res, "성공");
    }


    @ApiOperation(value = "북마크 추가", notes = "북마크를 추가 한다")
    @PostMapping(value = "/bookmark",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SingleResult<Boolean> BookmarkAdd(@Valid @RequestBody BookmarkReqDTO req) throws Exception {


        Bookmark bookmark = Bookmark.builder()
                .user(signService.findUserById(Long.parseLong(req.getId())))
                .handcontent(handService.findById(Long.parseLong(req.getHandcontent_key())))
                .build();

        handService.save(bookmark);

        return responseService.getSingleResult(true);
    }

    //북마크 추가 for Android
    @ApiOperation(value = "북마크 추가", notes = "북마크를 추가 한다")
    @PostMapping(value = "/bookmark",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SingleResult<Boolean> BookmarkAddForAndroid(@Valid @RequestBody BookmarkReqDTO req) throws Exception {


        Bookmark bookmark = Bookmark.builder()
                .user(signService.findUserById(Long.parseLong(req.getId())))
                .handcontent(handService.findById(Long.parseLong(req.getHandcontent_key())))
                .build();

        handService.save(bookmark);

        return responseService.getSingleResult(true);
    }

    @ApiOperation(value = "북마크 삭제", notes = "북마크를 삭제한다")
    @DeleteMapping(value = "/bookmark", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SingleResult<Boolean> BookmarkDelete(@Valid @RequestBody BookmarkReqDTO req) throws Exception {

        handService.delete(Long.parseLong(req.getId()), Long.parseLong(req.getHandcontent_key()));
        return responseService.getSingleResult(true);
    }

}
