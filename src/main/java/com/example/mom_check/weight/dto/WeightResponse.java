package com.example.mom_check.weight.dto;

import com.example.mom_check.common.type.WeightStatusType;
import com.example.mom_check.weight.domain.Weight;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class WeightResponse {
    private UUID id;
    private String date;
    private Double weight;
    private WeightStatusType status;
    private LocalDateTime createdAt;

    public static WeightResponse toDto(Weight weight) {
        return new WeightResponse(
                weight.getId(),
                weight.getDate(),
                weight.getWeight(),
                weight.getStatus(),
                weight.getCreatedAt()
        );
    }
}
