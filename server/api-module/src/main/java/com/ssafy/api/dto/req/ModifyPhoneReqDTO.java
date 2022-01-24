package com.ssafy.api.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyPhoneReqDTO {

    @NotBlank
    @ApiModelProperty(value = "유저 번호", required = true, example = "1")
    private String id;

    @NotBlank
    @ApiModelProperty(value = "핸드폰번호('-'값 없이 입력)", required = false, example = "01012345678")
    private String phone;
}
