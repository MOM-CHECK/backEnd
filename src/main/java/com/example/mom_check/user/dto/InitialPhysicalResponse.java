package com.example.mom_check.user.dto;

import com.example.mom_check.auth.dto.LoginResponse;
import com.example.mom_check.baby.domain.Baby;
import com.example.mom_check.user.domain.InitialPhysical;
import com.example.mom_check.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class InitialPhysicalResponse {
    private String email;
    private Double height;
    private Double weight;
    private Double bmi;
    private LocalDateTime createdAt;

    public static InitialPhysicalResponse toDto(User user, InitialPhysical initialPhysical) {
        return new InitialPhysicalResponse(
                user.getEmail(),
                initialPhysical.getHeight(),
                initialPhysical.getWeight(),
                initialPhysical.getBmi(),
                initialPhysical.getCreatedAt()
        );
    }
}
