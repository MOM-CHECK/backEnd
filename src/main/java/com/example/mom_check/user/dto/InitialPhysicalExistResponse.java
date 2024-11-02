package com.example.mom_check.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InitialPhysicalExistResponse {
    private Boolean isExist;

    public static InitialPhysicalExistResponse toDto(Boolean isExist) {
        return new InitialPhysicalExistResponse(isExist);
    }
}
