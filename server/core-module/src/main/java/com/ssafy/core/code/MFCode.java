package com.ssafy.core.code;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// 성별로 사용될 코드값
public enum MFCode implements BaseEnumCode<String> {

    M("M"),
    F("F"),
    NULL("");

    private final String value;
}
