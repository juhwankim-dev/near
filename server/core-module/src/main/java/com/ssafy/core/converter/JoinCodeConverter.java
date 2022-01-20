package com.ssafy.core.converter;

import com.ssafy.core.code.JoinCode;

import javax.persistence.Converter;


@Converter(autoApply = true)
// 가입 타입 코드값의 Converter
public class JoinCodeConverter extends AbstractBaseEnumConverter<JoinCode, String> {

    @Override
    protected JoinCode[] getValueList() {
        return JoinCode.values();
    }
}
