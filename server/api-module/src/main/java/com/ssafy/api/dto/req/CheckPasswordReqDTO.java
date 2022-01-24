package com.ssafy.api.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckPasswordReqDTO {


    @NotBlank
    @ApiModelProperty(value = "토큰값", required = true, example = "")
    private String token;


    @NotBlank
    @ApiModelProperty(value = "현재 비밀번호", required = true, example = "test123!")
    private String password;

}
