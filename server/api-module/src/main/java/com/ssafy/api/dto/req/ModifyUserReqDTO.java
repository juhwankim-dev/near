package com.ssafy.api.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyUserReqDTO {

    @NotBlank
    @ApiModelProperty(value = "유저 번호", required = true, example = "1")
    private String id;

    @NotBlank
    @ApiModelProperty(value = "변경 비밀번호", required = true, example = "test123!")
    private String password;

    @NotBlank
    @ApiModelProperty(value = "닉네임", required = true, example = "카카오")
    private String nickname;

    @NotBlank
    @ApiModelProperty(value = "주소", required = true, example = "경북 구미시 3공단 3로 ")
    private String address;

    @NotBlank
    @ApiModelProperty(value = "상세주소", required = true, example = "302 임수동 94-1 멀티캠퍼스")
    private String addressDetail;

    @NotBlank
    @ApiModelProperty(value = "핸드폰번호('-'값 없이 입력)", required = true, example = "01012345678")
    private String phone;

    @NotBlank
    @ApiModelProperty(value = "이메일 주소", required = true, example = "kakao@gmail.com")
    private String email;

}
