package com.ssafy.core.converter;


import com.ssafy.core.code.YNCode;

import javax.persistence.Converter;


@Converter(autoApply = true)
// YN 코드값의 Converter
public class YNCodeConverter extends AbstractBaseEnumConverter<YNCode, String> {

    @Override
    protected YNCode[] getValueList() {
        return YNCode.values();
    }
}
