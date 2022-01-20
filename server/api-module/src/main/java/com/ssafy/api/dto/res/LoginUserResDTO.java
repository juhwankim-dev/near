package com.ssafy.api.dto.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserResDTO {
    @ApiModelProperty(value = "회원 아이디", required = true,example = "1")
    private long id;

    @ApiModelProperty(value="토큰값", required = true, example = "")
    private String token;
}
