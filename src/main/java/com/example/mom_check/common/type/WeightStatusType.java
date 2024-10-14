package com.example.mom_check.common.type;

import com.example.mom_check.common.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

import static com.example.mom_check.common.exception.ErrorCode.WRONG_SEX_TYPE;

@Getter
@AllArgsConstructor
public enum WeightStatusType {
    GOOD("GOOD"),
    UNDER("UNDER"),
    OVER("OVER");

    private final String value;

    @JsonCreator
    public static WeightStatusType fromStatusType(String value) {
        return Arrays.stream(values())
                .filter(type -> type.getValue().equals(value))
                .findAny()
                .orElseThrow(() -> new BusinessException(WRONG_SEX_TYPE));
    }
}
