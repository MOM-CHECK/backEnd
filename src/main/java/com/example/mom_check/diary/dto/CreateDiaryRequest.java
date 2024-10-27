package com.example.mom_check.diary.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @NotBlank(message = "날짜를 입력하지 않았습니다.")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "날짜는 yyyy-MM-dd 형식이어야 합니다.")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private String date;
}
