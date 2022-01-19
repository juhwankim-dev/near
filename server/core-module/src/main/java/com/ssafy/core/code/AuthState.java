package com.ssafy.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// 인증 상태로 사용될 상태 코드값
public enum AuthState implements BaseEnumCode<String> {

    NONE("none"),
    Y("Y"),
    N("N"),
    YES("Y"),
    NO("N");

    private final String value;
}
