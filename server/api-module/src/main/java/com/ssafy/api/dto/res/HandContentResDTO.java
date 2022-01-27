package com.ssafy.api.dto.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HandContentResDTO {

    @ApiModelProperty(value = "수어 번호", required = true, example = "1")
    private long handcontent_key;

    // 수어 의미
    @ApiModelProperty(value = "수어 의미", required = true, example = "사랑")
    private String mean;

    @ApiModelProperty(value = "수어 이름", required = true, example = "사랑")
    private String name;

    // 수어 설명
    @ApiModelProperty(value = "수어 동작 설명", required = true, example = "손가락으로 v를 만든다")
    private String explanation;

    //자음 0, 모음 1
    @ApiModelProperty(value = "동영상 경로", required = true, example = "url")
    private String video_path;

    // 이미지 경로
    @ApiModelProperty(value = "이미지 경로", required = true, example = "url")
    private String image_path;


}
