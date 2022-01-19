package com.ssafy.api.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckEmailReqDTO {

    @NotBlank
    @ApiModelProperty(value = "이메일", required = false, example = "kakao123@test.com")
    private String email;

}

