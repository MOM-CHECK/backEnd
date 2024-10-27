package com.example.mom_check.diary.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDiaryRequest {
    @NotBlank(message = "제목을 입력하지 않았습니다.")
    private String title;

    @NotBlank(message = "아이콘을 입력하지 않았습니다.")
    private String icon;

    @NotBlank(message = "내용을 입력하지 않았습니다.")
    private String content;

    @NotBlank(message = "날짜를 입력하지 않았습니다.")
    private String date;
}
