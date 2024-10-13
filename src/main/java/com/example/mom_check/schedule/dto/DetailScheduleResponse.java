package com.example.mom_check.schedule.dto;

import com.example.mom_check.schedule.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class DetailScheduleResponse {
    private UUID id;
    private String date;
    private LocalTime time;
    private String color;
    private String content;
    private LocalDateTime createdAt;

    public static DetailScheduleResponse toDto(Schedule schedule) {
        return new DetailScheduleResponse(
                schedule.getId(),
                schedule.getDate(),
                schedule.getTime(),
                schedule.getColor(),
                schedule.getContent(),
                schedule.getCreatedAt()
        );
    }
}
