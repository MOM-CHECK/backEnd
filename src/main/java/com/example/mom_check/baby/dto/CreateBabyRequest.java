package com.example.mom_check.baby.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBabyRequest {
    @NotBlank(message = "이름을 입력하지 않았습니다.")
    private String name;

    @NotBlank(message = "출산예정일을 입력하지 않았습니다.")
    private String expectedBirth;

    @NotBlank(message = "성별을 입력하지 않았습니다.")
    private String sex;
}
