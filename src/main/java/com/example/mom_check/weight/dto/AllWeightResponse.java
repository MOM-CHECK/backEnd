package com.example.mom_check.weight.dto;

import com.example.mom_check.weight.domain.Weight;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class AllWeightResponse {
    private UUID id;
    private LocalDate date;
    private Double weight;

    public static AllWeightResponse toDto(Weight weight) {
        return new AllWeightResponse(
                weight.getId(),
                weight.getDate(),
                weight.getWeight()
        );
    }
}
