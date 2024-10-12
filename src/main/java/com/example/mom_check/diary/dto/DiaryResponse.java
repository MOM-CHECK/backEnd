package com.example.mom_check.diary.dto;

import com.example.mom_check.diary.domain.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class DiaryResponse {
    private UUID id;
    private String date;
    private String icon;
    private String title;
    private LocalDateTime createdAt;

    public static DiaryResponse toDto(Diary diary) {
        return new DiaryResponse(
                diary.getId(),
                diary.getDate(),
                diary.getIcon(),
                diary.getTitle(),
                diary.getCreatedAt()
        );
    }
}
