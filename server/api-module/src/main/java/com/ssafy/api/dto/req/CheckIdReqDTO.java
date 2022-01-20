package com.ssafy.api.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckIdReqDTO {
    @NotBlank
    @ApiModelProperty(value = "uid(일반회원: 아이디, sns로그인: uid값", required = true, example = "kakao123")
    private String uid;
}
