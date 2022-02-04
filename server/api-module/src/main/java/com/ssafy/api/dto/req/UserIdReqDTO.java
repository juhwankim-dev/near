package com.ssafy.api.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserIdReqDTO {

    @NotNull
    @ApiModelProperty(value="유저 번호", required = true, example = "1")
    private String id;
}
