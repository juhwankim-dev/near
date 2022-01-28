package com.ssafy.api.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class tokenReqDTO {

    @NotNull
    @ApiModelProperty(value="토큰값", required = true, example = "")
    private String token;
}
