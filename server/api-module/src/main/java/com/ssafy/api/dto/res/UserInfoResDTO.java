package com.ssafy.api.dto.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResDTO {


    @ApiModelProperty(value = "uid (일반회원:아이디, sns로그인:uid값)", required = true, example = "kakao123")
    private String uid;

    @ApiModelProperty(value = "비밀번호", required = true, example = "123")
    private String password;

    @ApiModelProperty(value = "이름", required = false, example = "카카오")
    private String name;

    // 닉네임 추가
    @ApiModelProperty(value = "닉네임", required = false, example = "닉네임")
    private String nickname;

    @ApiModelProperty(value = "이메일", required = false, example = "kakao123@test.com")
    private String email;

    @ApiModelProperty(value = "핸드폰번호('-'값 없이 입력)", required = false, example = "01012345678")
    private String phone;

    @ApiModelProperty(value = "주소", required = false, example = "")
    private String address;

    @ApiModelProperty(value = "상세주소", required = false, example = "")
    private String addressDetail;
}
