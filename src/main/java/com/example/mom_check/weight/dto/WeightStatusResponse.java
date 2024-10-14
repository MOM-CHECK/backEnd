package com.example.mom_check.weight.dto;

import com.example.mom_check.common.type.WeightStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WeightStatusResponse {
    private Integer week;
    private Double initialWeight;
    private Double weight;
    private WeightStatusType status;
    private Double min;
    private Double max;

    public static WeightStatusResponse toDto(Integer week, Double initialWeight, Double weight, WeightStatusType status, Double min, Double max) {
        return new WeightStatusResponse(week, initialWeight, weight, status, min, max);
    }
}
