package com.ssafy.core.converter;

import com.ssafy.core.code.MFCode;

import javax.persistence.Converter;


@Converter(autoApply = true)
// 성별 코드값의 Converter
public class MFCodeConverter extends AbstractBaseEnumConverter<MFCode, String> {

    @Override
    protected MFCode[] getValueList() {
        return MFCode.values();
    }
}
