package com.ssafy.api.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyAddressReqDTO {

    @NotBlank
    @ApiModelProperty(value = "유저 번호", required = true, example = "유저 번호")
    private String id;

    @NotBlank
    @ApiModelProperty(value = "주소", required = false, example = "경북 구미시 3공단 3로 ")
    private String address;

    @NotBlank
    @ApiModelProperty(value = "상세주소", required = false, example = "302 임수동 94-1 멀티캠퍼스")
    private String addressDetail;
}
