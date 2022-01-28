package com.ssafy.core.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// 가입 타입으로 사용될 코드값
public enum JoinCode implements BaseEnumCode<String> {

    none("none"),
    sns("sns"),
    NULL("");

    private final String value;
}

