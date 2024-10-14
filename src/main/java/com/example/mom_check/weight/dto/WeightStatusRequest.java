package com.example.mom_check.weight.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WeightStatusRequest {
    @NotBlank(message = "날짜를 입력하지 않았습니다.")
    private String date;
    
    @NotNull(message = "몸무게를 입력하지 않았습니다.")
    private Double weight;
}
