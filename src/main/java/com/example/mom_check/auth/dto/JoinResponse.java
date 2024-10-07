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

    public static JoinResponse toDto(User user) {
        return new JoinResponse(user.getEmail(), user.getNickname(), user.getPartnerPhone());
    }
}
