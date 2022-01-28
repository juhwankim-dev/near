package com.ssafy.api.dto.req;

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
public class LoginUserReqDTO {
    @NotBlank
    @ApiModelProperty(value = "uid(일반회원: 아이디, sns로그인: uid값", required = true, example = "abc")
    private String uid;

    @NotBlank
    @ApiModelProperty(value = "비밀번호", required = true, example = "123")
    private String password;

    @ApiModelProperty(value = "토큰값", required = false, example = "")
    private String token;


    @NotNull
    @Pattern(regexp = "^(none|sns)$")
    @ApiModelProperty(value = "로그인 타입 (none, sns)", required = true, example = "none")
    private String type;
}
