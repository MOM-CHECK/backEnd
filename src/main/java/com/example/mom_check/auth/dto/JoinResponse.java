package com.example.mom_check.auth.dto;

import com.example.mom_check.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JoinResponse {
    private String email;
    private String nickname;
    private String partnerPhone;
    private String accessToken;

    public static JoinResponse toDto(User user, String accessToken) {
        return new JoinResponse(user.getEmail(), user.getNickname(), user.getPartnerPhone(), accessToken);
    }
}
