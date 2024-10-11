package com.example.mom_check.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InitialPhysicalRequest {
    @NotNull(message = "키를 입력하지 않았습니다.")
    private Double height;

    @NotNull(message = "몸무게를 입력하지 않았습니다.")
    private Double weight;

    @NotNull(message = "BMI를 입력하지 않았습니다.")
    private Double bmi;
}
