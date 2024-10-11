package com.example.mom_check.auth.dto;

import com.example.mom_check.baby.domain.Baby;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String babyName;
    private String expectedBirth;

    public static LoginResponse toDto(String accessToken, Baby baby) {
        return new LoginResponse(accessToken, baby.getName(), baby.getExpectedBirth());
    }
}
