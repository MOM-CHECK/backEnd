package com.example.mom_check.diary.dto;

import com.example.mom_check.diary.domain.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class DetailDiaryResponse {
    private UUID id;
    private Instant date;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public static DetailDiaryResponse toDto(Diary diary) {
        return new DetailDiaryResponse(
                diary.getId(),
                diary.getDate(),
                diary.getTitle(),
                diary.getContent(),
                diary.getCreatedAt()
        );
    }
}
