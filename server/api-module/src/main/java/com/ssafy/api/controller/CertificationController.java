package com.ssafy.api.controller;

import com.ssafy.api.dto.MailDto;
import com.ssafy.api.service.ModifyService;
import com.ssafy.api.service.SignService;
import com.ssafy.api.service.common.ResponseService;
import com.ssafy.api.service.common.SingleResult;
import com.ssafy.core.entity.User;
import com.ssafy.core.exception.ApiMessageException;
import com.ssafy.core.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"01. 인증"})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/certification")
@CrossOrigin(origins = "*")
public class CertificationController {
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final ModifyService modifyService;
    private final SignService signService;

    @PostMapping("/mail")
    @ApiOperation(value = "메일 전송", notes = "주소, 제목, 메시지 내용을 받아와서 전송한다")
    public SingleResult<String> execMail(@RequestBody MailDto mailDto) {

        mailService.sendSimpleMail(mailDto.getAddress(), mailDto.getTitle(), mailDto.getMessage());

        return responseService.getSingleResult("성공");
    }

    @GetMapping("/id/{email}")
    @ApiOperation(value = "아이디 찾기", notes = "유저의 이메일에 따른 아이디를 반환한다")
    public SingleResult<String> searchId(@ApiParam(value = "이메일") @PathVariable String email) {

        User user = signService.findUserByEmail(email);
        if (user == null) {
            throw new ApiMessageException("이메일이 존재하지 않습니다");
        }

        return responseService.getSingleResult(user.getUid());
    }

    @GetMapping("/password/{email}")
    @ApiOperation(value = "비밀번호 찾기", notes = "유저의 이메일에 따른 비밀번호 메일로 보낸다")
    public SingleResult<Boolean> searchPassword(@ApiParam(value = "이메일") @PathVariable String email) {

        User user = signService.findUserByEmail(email);
        if (user == null) {
            throw new ApiMessageException("이메일이 존재하지 않습니다");
        }

        String ramdomPassword = mailService.getRamdomPassword(10);


        // 비밀번호 변경
        if (!modifyService.updatePassword(user.getId(), passwordEncoder.encode(ramdomPassword))) {
            throw new ApiMessageException("비밀번호 변경 실패");
        }

        mailService.sendHtmlMail(email, "Near 비밀번호 변경안내", "회원님의 비밀번호가 " + ramdomPassword + "로 변경되었습니다");
        return responseService.getSingleResult(true);
    }


    @GetMapping("/{email}")
    @ApiOperation(value = "인증번호 설정", notes = "이메일 확인을 위해 인증번호를 보낸다")
    public SingleResult<String> certificationEmail(@ApiParam(value = "이메일") @PathVariable String email) {

        String CertificationCode = mailService.getRamdomPassword(5);

        mailService.sendSimpleMail(email, "Near 회원가입 인증번호안내", "Near 가입 인증 번호 : " + CertificationCode + " 입니다");

        return responseService.getSingleResult(CertificationCode);
    }
}
