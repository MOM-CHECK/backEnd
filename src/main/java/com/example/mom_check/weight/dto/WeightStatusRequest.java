package com.example.mom_check.weight.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WeightStatusRequest {
    @NotNull(message = "날짜를 입력하지 않았습니다.")
    private LocalDate date;
}
