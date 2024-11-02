package com.example.mom_check.auth.dto;

import com.example.mom_check.baby.domain.Baby;
import com.example.mom_check.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private UUID userId;
    private UUID babyId;
    private String accessToken;

    public static LoginResponse toDto(User user, String accessToken, Baby baby) {
        return new LoginResponse(user.getId(), baby.getId(), accessToken);
    }
}
