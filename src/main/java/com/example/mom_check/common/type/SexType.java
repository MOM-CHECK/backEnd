package com.example.mom_check.common.type;

import com.example.mom_check.common.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

import static com.example.mom_check.common.exception.ErrorCode.WRONG_SEX_TYPE;

@Getter
@AllArgsConstructor
public enum SexType {
    GIRL("GIRL"),
    BOY("BOY"),
    X("X");

    private final String value;

    @JsonCreator
    public static SexType fromSexType(String value) {
        return Arrays.stream(values())
                .filter(type -> type.getValue().equals(value))
                .findAny()
                .orElseThrow(() -> new BusinessException(WRONG_SEX_TYPE));
    }
}
