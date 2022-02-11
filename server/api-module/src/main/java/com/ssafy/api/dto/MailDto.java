package com.ssafy.api.dto;

import com.ssafy.core.service.MailService;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {


    @NotNull
    @ApiModelProperty(name = "주소", required = true, example = "near@google.com")
    private String address;

    @NotNull
    @ApiModelProperty(name = "제목", required = true, example = "제목")
    private String title;

    @NotNull
    @ApiModelProperty(name = "메시지", required = true, example = "메세지 내용")
    private String message;

}
