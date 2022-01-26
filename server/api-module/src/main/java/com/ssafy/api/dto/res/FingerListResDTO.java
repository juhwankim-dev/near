package com.ssafy.api.dto.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FingerListResDTO {

    @ApiModelProperty(value = "지문자 번호", required = true,example = "1")
    private long fingercotent_key;

    @ApiModelProperty(value = "지문자", required = true,example = "ㄱ")
    @Column(nullable = true, length = 2)
    private String name;

}
