package com.ssafy.api.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckNicknameReqDTO {

    @ApiModelProperty(value = "닉네임", required = false, example = "닉네임")
    private String nickname;

}
