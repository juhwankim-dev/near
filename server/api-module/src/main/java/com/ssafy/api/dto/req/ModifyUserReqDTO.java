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
    @ApiModelProperty(value = "이메일 주소", required = true, example = "kakao@gmail.com")
    private String email;

}
