package com.example.mom_check.diary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDiaryRequest {
    @NotBlank(message = "제목을 입력하지 않았습니다.")
    private String title;

    @NotBlank(message = "아이콘을 입력하지 않았습니다.")
    private String icon;

    @NotBlank(message = "내용을 입력하지 않았습니다.")
    private String content;

    @NotNull(message = "날짜을 입력하지 않았습니다.")
    private Instant date;
}
