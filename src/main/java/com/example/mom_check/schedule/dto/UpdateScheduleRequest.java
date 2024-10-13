package com.example.mom_check.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateScheduleRequest {
    @NotBlank(message = "날짜를 입력하지 않았습니다.")
    private String date;

    private LocalTime time;

    @NotBlank(message = "색깔을 입력하지 않았습니다.")
    private String color;

    @NotBlank(message = "내용을 입력하지 않았습니다.")
    private String content;
}
