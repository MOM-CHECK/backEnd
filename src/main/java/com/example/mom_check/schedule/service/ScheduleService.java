package com.example.mom_check.schedule.service;

import com.example.mom_check.common.exception.BusinessException;
import com.example.mom_check.schedule.domain.Schedule;
import com.example.mom_check.schedule.dto.AllScheduleResponse;
import com.example.mom_check.schedule.dto.CreateSchduleRequest;
import com.example.mom_check.schedule.dto.DetailScheduleResponse;
import com.example.mom_check.schedule.dto.UpdateScheduleRequest;
import com.example.mom_check.schedule.repository.ScheduleRepository;
import com.example.mom_check.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.example.mom_check.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public DetailScheduleResponse createSchedule(User user, CreateSchduleRequest req) {
        Schedule schedule = Schedule.builder()
                .content(req.getContent())
                .date(req.getDate())
                .time(req.getTime())
                .color(req.getColor())
                .user(user)
                .build();

        scheduleRepository.save(schedule);

        return DetailScheduleResponse.toDto(schedule);
    }

    @Transactional
    public DetailScheduleResponse findById(User user, UUID id) {
        Schedule schedule = findByIdAndUser(id, user);
        return DetailScheduleResponse.toDto(schedule);
    }

    @Transactional
    public List<AllScheduleResponse> findAllByDate(User user, String date) {
        List<Schedule> schedules = findByDateAndUser(date, user);
        if (schedules != null) {
            return schedules.stream().map(AllScheduleResponse::toDto).toList();
        } else {
            return null;
        }
    }

    @Transactional
    public DetailScheduleResponse editSchedule(User user, UUID id, UpdateScheduleRequest req) {
        Schedule schedule = scheduleRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new BusinessException(SCHEDULE_NOT_FOUND));

        schedule.update(
                req.getDate(),
                req.getTime(),
                req.getColor(),
                req.getContent()
        );

        return DetailScheduleResponse.toDto(schedule);
    }

    @Transactional
    public void deleteSchedule(UUID id, User user) {
        Schedule schedule = findByIdAndUser(id, user);
        scheduleRepository.delete(schedule);
    }

    private Schedule findByIdAndUser(UUID id, User user) {
        return scheduleRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new BusinessException(SCHEDULE_NOT_FOUND));
    }

    private List<Schedule> findByDateAndUser(String date, User user) {
        return scheduleRepository.findAllByDateAndUserOrderByTimeAsc(date, user)
                .orElse(null);
    }
}
