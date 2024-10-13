package com.example.mom_check.schedule.dto;

import com.example.mom_check.schedule.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class AllScheduleResponse {
    private UUID id;
    private LocalTime time;
    private String color;
    private String content;

    public static AllScheduleResponse toDto(Schedule schedule) {
        return new AllScheduleResponse(
                schedule.getId(),
                schedule.getTime(),
                schedule.getColor(),
                schedule.getContent()
        );
    }
}
