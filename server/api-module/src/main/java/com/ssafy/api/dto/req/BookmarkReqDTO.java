package com.ssafy.api.dto.req;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkReqDTO {

    @NotNull
    @ApiModelProperty(value = "유저 번호", required = true, example = "1")
    private String id;

    @NotNull
    @ApiModelProperty(value = "수어 콘텐츠 번호", required = true, example = "1")
    private String handcontent_key;

}
