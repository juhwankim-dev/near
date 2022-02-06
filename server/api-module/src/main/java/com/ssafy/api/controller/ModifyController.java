package com.ssafy.api.controller;

import com.ssafy.api.config.security.JwtTokenProvider;
import com.ssafy.api.dto.req.*;
import com.ssafy.api.service.ModifyService;
import com.ssafy.api.service.SignService;
import com.ssafy.api.service.common.CommonResult;
import com.ssafy.api.service.common.ResponseService;
import com.ssafy.core.entity.User;
import com.ssafy.core.exception.ApiMessageException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"03. 유저 정보 변경"})
@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/modify")
public class ModifyController {
    private final ModifyService modifyService;
    private final SignService signService;
    private final PasswordEncoder passwordEncoder;
    private final ResponseService responseService;
    private final JwtTokenProvider jwtTokenProvider;


    @ApiOperation(value = "비밀번호 확인", notes = "비밀번호 일치 여부에 따라 성공, 실패를 반환한다.")
    @PostMapping(value = "/check", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CommonResult CheckPassword(@Valid @RequestBody CheckPasswordReqDTO req) throws Exception {
        // 1. 유효기간을 확인한다.
        if (!jwtTokenProvider.validateToken(req.getToken())) {
            throw new ApiMessageException("유효기간이 만료된 토큰입니다.");
        }

        // 2. 토큰으로 들어온 유저를 반환한다.
        User result = signService.findUserById(Long.parseLong(jwtTokenProvider.getUserPk(req.getToken())));

        // 3. 유저 db에 저장되어 있는 토큰과 받아온 토큰이 일치하는지 판단 한다.
        if (!result.getToken().equals(req.getToken())) {
            throw new ApiMessageException("다른 곳에서 로그인 한 아이디 입니다.");
        }

        //현재 비밀번호가 같지 않을 때
        if (!passwordEncoder.matches(req.getPassword(), result.getPassword())) {
            throw new ApiMessageException("현재 비밀번호가 일치하지 않습니다");
        }

        return responseService.getSingleResult("성공");
    }

    //ForAndroid
    @ApiOperation(value = "비밀번호 확인", notes = "비밀번호 일치 여부에 따라 성공, 실패를 반환한다.")
    @PostMapping(value = "/check", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CommonResult CheckPasswordForAndroid(@Valid CheckPasswordReqDTO req) throws Exception {
        // 1. 유효기간을 확인한다.
        if (!jwtTokenProvider.validateToken(req.getToken())) {
            throw new ApiMessageException("유효기간이 만료된 토큰입니다.");
        }

        // 2. 토큰으로 들어온 유저를 반환한다.
        User result = signService.findUserById(Long.parseLong(jwtTokenProvider.getUserPk(req.getToken())));

        // 3. 유저 db에 저장되어 있는 토큰과 받아온 토큰이 일치하는지 판단 한다.
        if (!result.getToken().equals(req.getToken())) {
            throw new ApiMessageException("다른 곳에서 로그인 한 아이디 입니다.");
        }

        //현재 비밀번호가 같지 않을 때
        if (!passwordEncoder.matches(req.getPassword(), result.getPassword())) {
            throw new ApiMessageException("현재 비밀번호가 일치하지 않습니다");
        }

        return responseService.getSingleResult("성공");
    }


    /**
     * 2022-01-21 by 김영훈
     *
     * @param req 유저의 토큰과 바꿀 비밀번호를 받아온다.
     * @return 성공 실패를 반환한다.
     * @brief 유저의 비밀번호를 업데이트 한다.
     * @date 2022-01-21
     */
    @ApiOperation(value = "비밀번호 수정", notes = "변경에 따라 성공, 실패를 반환한다.")
    @PutMapping(value = "/password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CommonResult ModifyPassword(@Valid @RequestBody ModifyPasswordReqDTO req) throws Exception {

        if (!modifyService.updatePassword(Long.parseLong(req.getId()), passwordEncoder.encode(req.getPassword()))) {
            throw new ApiMessageException("실패");
        }

        return responseService.getSingleResult("성공");
    }

    //ForAndroid
    @ApiOperation(value = "비밀번호 수정", notes = "변경에 따라 성공, 실패를 반환한다.")
    @PutMapping(value = "/password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CommonResult ModifyPasswordForAndroid(@Valid ModifyPasswordReqDTO req) throws Exception {

        if (!modifyService.updatePassword(Long.parseLong(req.getId()), passwordEncoder.encode(req.getPassword()))) {
            throw new ApiMessageException("실패");
        }

        return responseService.getSingleResult("성공");
    }

    /**
     * 2022-01-24 by 김영훈
     *
     * @param req 변경할 주소를 받아온다.
     * @return 성공 실패를 반환한다.
     * @brief 주소 변경 한다.
     * @date 2022-01-24
     */
    @ApiOperation(value = "주소 변경", notes = "변경에 따라 성공, 실패를 반환한다.")
    @PutMapping(value = "/address", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CommonResult ModifyAddress(@Valid @RequestBody ModifyAddressReqDTO req) throws Exception {

        if (!modifyService.updateAddress(Long.parseLong(req.getId()), req.getAddress(), req.getAddressDetail())) {
            throw new ApiMessageException("실페");
        }

        return responseService.getSingleResult("성공");
    }

    //ForAndroid
    @ApiOperation(value = "주소 변경", notes = "변경에 따라 성공, 실패를 반환한다.")
    @PutMapping(value = "/address", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CommonResult ModifyAddressForAndroid(@Valid ModifyAddressReqDTO req) throws Exception {

        if (!modifyService.updateAddress(Long.parseLong(req.getId()), req.getAddress(), req.getAddressDetail())) {
            throw new ApiMessageException("실페");
        }

        return responseService.getSingleResult("성공");
    }


    /**
     * 2022-01-24 by 김영훈
     *
     * @param req 변경할 휴대폰 번호를 받아온다
     * @return 성공 실패를 반환한다.
     * @brief 휴대폰 번호를 변경한다.
     * @date 2022-01-24
     */
    @ApiOperation(value = "휴대폰 번호 변경", notes = "변경에 따라 성공, 실패를 반환한다.")
    @PutMapping(value = "/phone", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CommonResult ModifyPhone(@Valid @RequestBody ModifyPhoneReqDTO req) throws Exception {

        if (!modifyService.updatePhone(Long.parseLong(req.getId()), req.getPhone())) {
            throw new ApiMessageException("실패");
        }

        return responseService.getSingleResult("성공");
    }

    //ForAndroid
    @ApiOperation(value = "휴대폰 번호 변경", notes = "변경에 따라 성공, 실패를 반환한다.")
    @PutMapping(value = "/phone", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CommonResult ModifyPhoneForAndroid(@Valid ModifyPhoneReqDTO req) throws Exception {

        if (!modifyService.updatePhone(Long.parseLong(req.getId()), req.getPhone())) {
            throw new ApiMessageException("실패");
        }

        return responseService.getSingleResult("성공");
    }

    /**
     * 2022-01-24 by 김영훈
     *
     * @param req 닉네임 받아온다
     * @return 성공 실패를 반환한다.
     * @brief 닉네임을 변경한다.
     * @date 2022-01-24
     */
    @ApiOperation(value = "닉네임 변경", notes = "변경에 따라 성공, 실패를 반환한다.")
    @PutMapping(value = "/nickname", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CommonResult ModifyNickname(@Valid @RequestBody ModifyNicknameReqDTO req) throws Exception {

        if (!modifyService.updateNickname(Long.parseLong(req.getId()), req.getNickname())) {
            throw new ApiMessageException("실패");
        }

        return responseService.getSingleResult("성공");
    }

    //ForAndroid
    @ApiOperation(value = "닉네임 변경", notes = "변경에 따라 성공, 실패를 반환한다.")
    @PutMapping(value = "/nickname", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CommonResult ModifyNicknameForAndroid(@Valid ModifyNicknameReqDTO req) throws Exception {

        if (!modifyService.updateNickname(Long.parseLong(req.getId()), req.getNickname())) {
            throw new ApiMessageException("실패");
        }

        return responseService.getSingleResult("성공");
    }

    /**
     * 2022-01-25 by 김영훈
     *
     * @param req 유저 이메일을 받아옴
     * @return 성공 실패
     * @brief 이메일 변경
     * @date 2022-01-25
     */
    @ApiOperation(value = "이메일 변경", notes = "변경에 따라 성공, 실패를 반환한다.")
    @PutMapping(value = "/email", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CommonResult ModifyEmail(@Valid @RequestBody ModifyEmailReqDTO req) throws Exception {

        if (!modifyService.updateNickname(Long.parseLong(req.getId()), req.getEmail())) {
            throw new ApiMessageException("실패");
        }

        return responseService.getSingleResult("성공");
    }

    //ForAndroid
    @ApiOperation(value = "이메일 변경", notes = "변경에 따라 성공, 실패를 반환한다.")
    @PutMapping(value = "/email", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CommonResult ModifyEmailForAndroid(@Valid ModifyEmailReqDTO req) throws Exception {

        if (!modifyService.updateNickname(Long.parseLong(req.getId()), req.getEmail())) {
            throw new ApiMessageException("실패");
        }

        return responseService.getSingleResult("성공");
    }

    /**
     * 2022-01-25 by 김영훈
     *
     * @param req 유저 변경 정보를 받아옴
     * @return 성공 실패
     * @brief 유저 정보 변경 완료
     * @date 2022-01-25
     */
    @ApiOperation(value = "유저정보 변경", notes = "변경에 따라 성공, 실패를 반환한다.")
    @PutMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CommonResult ModifyUser(@Valid @RequestBody ModifyUserReqDTO req) throws Exception {

        req.setPassword(passwordEncoder.encode(req.getPassword()));

        if (!modifyService.updateUser(Long.parseLong(req.getId()), req)) {
            throw new ApiMessageException("실패");
        }

        return responseService.getSingleResult("성공");
    }

    //ForAndroid
    @ApiOperation(value = "유저정보 변경", notes = "변경에 따라 성공, 실패를 반환한다.")
    @PutMapping(value = "/user", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CommonResult ModifyUserForAndroid(@Valid ModifyUserReqDTO req) throws Exception {

        req.setPassword(passwordEncoder.encode(req.getPassword()));

        if (!modifyService.updateUser(Long.parseLong(req.getId()), req)) {
            throw new ApiMessageException("실패");
        }

        return responseService.getSingleResult("성공");
    }

}
