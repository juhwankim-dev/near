package com.ssafy.api.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyPasswordReqDTO {

    @NotBlank
    @ApiModelProperty(value = "유저 번호", required = true, example = "1")
    private String id;

    @NotBlank
    @ApiModelProperty(value = "변경 비밀번호", required = true, example = "test123!")
    private String password;
}
