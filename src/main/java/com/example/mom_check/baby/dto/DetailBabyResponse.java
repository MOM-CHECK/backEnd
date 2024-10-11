package com.example.mom_check.baby.dto;

import com.example.mom_check.baby.domain.Baby;
import com.example.mom_check.common.type.SexType;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class DetailBabyResponse {
    UUID id;
    String name;
    String expectedBirth;
    SexType sex;
    LocalDateTime createdAt;

    public static DetailBabyResponse toDto(Baby baby) {
        return new DetailBabyResponse(
                baby.getId(),
                baby.getName(),
                baby.getExpectedBirth(),
                baby.getSex(),
                baby.getCreatedAt()
        );
    }
}
