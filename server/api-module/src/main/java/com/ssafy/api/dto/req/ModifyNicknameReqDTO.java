package com.ssafy.api.dto.req;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyNicknameReqDTO {

    @NotBlank
    @ApiModelProperty(value = "유저번호", required = true, example = "1")
    private String id;

    @NotBlank
    @ApiModelProperty(value = "닉네임", required = true, example = "카카오")
    private String nickname;
}

