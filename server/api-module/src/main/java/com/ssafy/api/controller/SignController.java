package com.ssafy.api.controller;

import com.ssafy.api.config.security.JwtTokenProvider;
import com.ssafy.api.dto.req.*;
import com.ssafy.api.dto.res.LoginUserResDTO;
import com.ssafy.api.dto.res.UserIdResDTO;
import com.ssafy.api.dto.res.UserInfoResDTO;
import com.ssafy.api.service.SignService;
import com.ssafy.api.service.common.ResponseService;
import com.ssafy.api.service.common.SingleResult;
import com.ssafy.api.service.common.CommonResult;
import com.ssafy.core.code.JoinCode;
import com.ssafy.core.code.MFCode;
import com.ssafy.core.code.YNCode;
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
import java.util.Collections;

@Api(tags = {"02. 회원"})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/sign")
@CrossOrigin(origins = "*")
public class SignController {
    private final SignService signService;
    private final PasswordEncoder passwordEncoder;
    private final ResponseService responseService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 로그인 : get /login
     * 회원탈퇴 : delete
     * 회원가입 일반 : post /signup
     * 회원가입 후 프로필등록 : post /regProfile
     * 소셜 가입 여부 체크 : get /exists/social
     */


    // 회원가입
    @ApiOperation(value = "회원가입", notes = "회원가입")
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SingleResult<UserIdResDTO> userSignUp(@Valid @RequestBody SignUpReqDTO req) throws Exception {
        // uid 중복되는 값이 존재하는지 확인 (uid = 고유한 값)
        User uidChk = signService.findByUid(req.getUid(), YNCode.Y);
        if (uidChk != null)
            throw new ApiMessageException("중복된 uid값의 회원이 존재합니다.");

        // DB에 저장할 User Entity 세팅
        User user = User.builder()
                .joinType(JoinCode.valueOf(req.getType()))
                .uid(req.getUid())
                .password(passwordEncoder.encode(req.getPassword()))
                .name(req.getName() == null ? "" : req.getName())
                .email(req.getEmail() == null ? "" : req.getEmail())
                .phone(req.getPhone())
                .address(req.getAddress())
                .addressDetail(req.getAddressDetail())
                .nickname(req.getNickname()) // 회원가입 닉네임 추가

                // 가입 후 프로필 등록으로 받을 데이터는 우선 기본값으로 세팅
                .age(0)
                .img("")
                .gender(MFCode.NULL)

                // 기타 필요한 값 세팅
                .push(YNCode.Y)
                .isBind(YNCode.Y)
                .roles(Collections.singletonList("ROLE_USER")) // 인증된 회원인지 확인하기 위한 JWT 토큰에 사용될 데이터
                .build();

        // 회원가입 (User Entity 저장)
        long userId = signService.userSignUp(user);
        // 저장된 User Entity의 PK가 없을 경우 (저장 실패)
        if (userId <= 0)
            throw new ApiMessageException("회원가입에 실패했습니다. 다시 시도해 주세요.");

        return responseService.getSingleResult(UserIdResDTO.builder().id(userId).build());
    }


    //로그인 for Android
    @ApiOperation(value = "회원가입", notes = "회원가입")
    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SingleResult<UserIdResDTO> userSignUpForAndroid(@Valid @RequestBody SignUpReqDTO req) throws Exception {
        // uid 중복되는 값이 존재하는지 확인 (uid = 고유한 값)
        User uidChk = signService.findByUid(req.getUid(), YNCode.Y);
        if (uidChk != null)
            throw new ApiMessageException("중복된 uid값의 회원이 존재합니다.");

        // DB에 저장할 User Entity 세팅
        User user = User.builder()
                .joinType(JoinCode.valueOf(req.getType()))
                .uid(req.getUid())
                .password(passwordEncoder.encode(req.getPassword()))
                .name(req.getName() == null ? "" : req.getName())
                .email(req.getEmail() == null ? "" : req.getEmail())
                .phone(req.getPhone())
                .address(req.getAddress())
                .addressDetail(req.getAddressDetail())
                .nickname(req.getNickname()) // 회원가입 닉네임 추가

                // 가입 후 프로필 등록으로 받을 데이터는 우선 기본값으로 세팅
                .age(0)
                .img("")
                .gender(MFCode.NULL)

                // 기타 필요한 값 세팅
                .push(YNCode.Y)
                .isBind(YNCode.Y)
                .roles(Collections.singletonList("ROLE_USER")) // 인증된 회원인지 확인하기 위한 JWT 토큰에 사용될 데이터
                .build();

        // 회원가입 (User Entity 저장)
        long userId = signService.userSignUp(user);
        // 저장된 User Entity의 PK가 없을 경우 (저장 실패)
        if (userId <= 0)
            throw new ApiMessageException("회원가입에 실패했습니다. 다시 시도해 주세요.");

        return responseService.getSingleResult(UserIdResDTO.builder().id(userId).build());
    }


    // 회원 탈퇴 API
    @ApiOperation(value = "회원탈퇴", notes = "회원탈퇴")
    @DeleteMapping(value = "/resign/{uid}")
    public @ResponseBody
    CommonResult resign(@PathVariable("uid") @ApiParam(value = "탈퇴할 회원의 uid(일반회원: 아이디, sns로그인: uid값", required = true) String uid) throws Exception {
//        // UID값과 회원가입 타입으로 해당되는 회원정보 조회
        User user = signService.findByUid(uid, YNCode.Y);
        if (user == null) {
            return responseService.getFailResult(0, "해당하는 아이디가 없습니다.");
        }
        signService.resign(user);
        return responseService.getSuccessResult("회원탈퇴에 성공했습니다.");
    }

    // 로그인
    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SingleResult<LoginUserResDTO> login(@Valid @RequestBody LoginUserReqDTO req) throws Exception {
        // UID값과 회원가입 타입으로 해당되는 회원정보 조회
        User user = signService.findUserByUidType(req.getUid(), JoinCode.valueOf(req.getType()));

        // 조회된 회원 정보가 없을 경우
        if (user == null) {
            throw new ApiMessageException("존재하지 않는 회원정보입니다. 입력 정보를 확인해 주세요.");
        }

        // 조회된 회원정보와 비밀번호 비교
        else if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new ApiMessageException("비밀번호가 일치하지 않습니다.");
        }

        // return 데이터 세팅
        LoginUserResDTO dto = LoginUserResDTO.builder()
                .id(user.getId()).build();

        // jwt token 세팅
        dto.setToken(jwtTokenProvider.createToken(String.valueOf(user.getId()), Collections.singletonList("ROLE_USER")));

        // 회원의 토큰값, 디바이스 정보 업데이트
        user.updateToken(dto.getToken());
        signService.saveUser(user);

        return responseService.getSingleResult(dto);
    }

    // 로그인
    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SingleResult<LoginUserResDTO> loginForAndroid(@Valid LoginUserReqDTO req) throws Exception {
        // UID값과 회원가입 타입으로 해당되는 회원정보 조회
        User user = signService.findUserByUidType(req.getUid(), JoinCode.valueOf(req.getType()));

        // 조회된 회원 정보가 없을 경우
        if (user == null) {
            throw new ApiMessageException("존재하지 않는 회원정보입니다. 입력 정보를 확인해 주세요.");
        }

        // 조회된 회원정보와 비밀번호 비교
        else if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new ApiMessageException("비밀번호가 일치하지 않습니다.");
        }

        // return 데이터 세팅
        LoginUserResDTO dto = LoginUserResDTO.builder()
                .id(user.getId()).build();

        // jwt token 세팅
        dto.setToken(jwtTokenProvider.createToken(String.valueOf(user.getId()), Collections.singletonList("ROLE_USER")));

        // 회원의 토큰값, 디바이스 정보 업데이트
        user.updateToken(dto.getToken());
        signService.saveUser(user);

        return responseService.getSingleResult(dto);
    }

    /**
     * 2022-01-18 by 김영훈
     *
     * @param req 아이디를 받아 옴
     * @return true : 회원 아이디 존재 false : 회원 아이디 없음
     * @brief 아이디 중복 확인
     * @date 2022-01-18
     */
    @ApiOperation(value = "아이디 중복 확인", notes = "중복에 따라 Ture, false를 반환한다.")
    @GetMapping(value = "/checkid", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SingleResult<Boolean> checkId(@Valid CheckIdReqDTO req) throws Exception {
        User user = signService.findByUid(req.getUid(), YNCode.Y);

        if (user == null) {
            return responseService.getSingleResult(false);
        }
        return responseService.getSingleResult(true);
    }

    /**
     * 2022-01-19 by 김영훈
     *
     * @param req 인자 설명
     * @return ture : 회원 이메일 존해 false : 회원 이메일 없음
     * @brief 이메일 중복 확인
     * @date 2022-01-19
     */
    @ApiOperation(value = "이메일 중복 확인", notes = "중복에 따라 Ture, false를 반환한다.")
    @GetMapping(value = "/checkemail", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SingleResult<Boolean> checkmail(@Valid CheckEmailReqDTO req) throws Exception {
        User user = signService.findUserByEmail(req.getEmail());

        if (user == null) {
            return responseService.getSingleResult(false);
        }
        return responseService.getSingleResult(true);
    }

    /**
     * 2022-01-20 by 김영훈
     *
     * @param req 유저 정보를 반환
     * @return 리턴값에 대한설명
     * @brief 토큰 값을 받아 회원정보 반환
     * @date 2022-01-20
     */
    @ApiOperation(value = "토큰으로 유저정보 반환", notes = "토큰에 따라 유저 정보를 반환한다")
    @PostMapping(value = "/userInfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SingleResult<UserInfoResDTO> userInfo(@Valid @RequestBody tokenReqDTO req) throws Exception {

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


        //4. 유저 정보 반환
        return responseService.getSingleResult(UserInfoResDTO.builder()
                .address(result.getAddress())
                .addressDetail(result.getAddressDetail())
                .email(result.getEmail())
                .name(result.getName())
                .uid(result.getUid())
                .password(result.getPassword())
                .nickname(result.getNickname())
                .phone(result.getPhone())
                .build());

    }

    @ApiOperation(value = "토큰으로 유저정보 반환", notes = "토큰에 따라 유저 정보를 반환한다")
    @PostMapping(value = "/userInfo", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SingleResult<UserInfoResDTO> userInfoforAndroid(@Valid tokenReqDTO req) throws Exception {

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


        //4. 유저 정보 반환
        return responseService.getSingleResult(UserInfoResDTO.builder()
                .address(result.getAddress())
                .addressDetail(result.getAddressDetail())
                .email(result.getEmail())
                .name(result.getName())
                .uid(result.getUid())
                .password(result.getPassword())
                .nickname(result.getNickname())
                .phone(result.getPhone())
                .build());

    }

    /**
     * 2022-01-20 by 김영훈
     *
     * @param req 닉네임을 받음
     * @return 회원 아이디 존재 false : 회원 아이디 없음
     * @brief 닉네임이 중복 결과를 반환한다.
     * @date 2022-01-20
     */
    @ApiOperation(value = "닉네임 중복 확인", notes = "중복에 따라 Ture, false를 반환한다.")
    @GetMapping(value = "/nickname", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    SingleResult<Boolean> checkNickname(@Valid CheckNicknameReqDTO req) throws Exception {
        User user = signService.findUserByNickname(req.getNickname());

        if (user == null) {
            return responseService.getSingleResult(false);
        }
        return responseService.getSingleResult(true);
    }


}
