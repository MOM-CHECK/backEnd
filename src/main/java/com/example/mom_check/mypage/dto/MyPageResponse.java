package com.example.mom_check.mypage.dto;

import com.example.mom_check.baby.domain.Baby;
import com.example.mom_check.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class MyPageResponse {
    private UUID userId;
    private String userNickname;
    private String userEmail;
    private UUID babyId;
    private String babyName;

    public static MyPageResponse toDto(User user, Baby baby) {
        return new MyPageResponse(
                user.getId(),
                user.getNickname(),
                user.getEmail(),
                baby.getId(),
                baby.getName()
        );
    }
}
