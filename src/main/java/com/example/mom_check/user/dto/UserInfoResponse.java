package com.example.mom_check.user.dto;

import com.example.mom_check.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserInfoResponse {
    private String nickname;
    private String email;
    private String partnerPhone;

    public static UserInfoResponse toDto(User user) {
        return new UserInfoResponse(
                user.getNickname(),
                user.getEmail(),
                user.getPartnerPhone()
        );
    }
}
