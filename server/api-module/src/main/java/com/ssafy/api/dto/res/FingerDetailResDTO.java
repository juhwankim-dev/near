package com.ssafy.api.dto.res;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FingerDetailResDTO {

    @ApiModelProperty(value = "지문자 번호", required = true, example = "1")
    private long fingercotent_key;

    @ApiModelProperty(value = "지문자", required = true, example = "ㄱ")
    @Column(nullable = true, length = 2)
    private String name;

    // 이미지 경로
    @ApiModelProperty(value = "이미지 경로", required = true, example = "url")
    @Column(nullable = true, length = 100)
    private String image_path;

    // 수어 설명
    @ApiModelProperty(value = "지문자 동작 설명", required = true, example = "손가락으로 v를 만든다")
    @Column(nullable = true, length = 255)
    private String explanation;
}
