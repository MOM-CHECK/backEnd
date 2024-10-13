package com.example.mom_check.schedule.controller;

import com.example.mom_check.common.annotation.Login;
import com.example.mom_check.common.response.CustomResponse;
import com.example.mom_check.schedule.dto.AllScheduleResponse;
import com.example.mom_check.schedule.dto.CreateSchduleRequest;
import com.example.mom_check.schedule.dto.DetailScheduleResponse;
import com.example.mom_check.schedule.dto.UpdateScheduleRequest;
import com.example.mom_check.schedule.service.ScheduleService;
import com.example.mom_check.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse createSchedule(@Login User user, @RequestBody CreateSchduleRequest req) {
        DetailScheduleResponse schedule = scheduleService.createSchedule(user, req);
        return CustomResponse.response(HttpStatus.CREATED, "일정을 정상적으로 작성했습니다.", schedule);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse edidSchedule(@PathVariable("id") UUID id, @Validated @RequestBody UpdateScheduleRequest req, @Login User user) {
        DetailScheduleResponse schedule = scheduleService.editSchedule(user, id, req);
        return CustomResponse.response(HttpStatus.OK, "일정을 정상적으로 수정했습니다.", schedule);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse findById(@PathVariable("id") UUID id, @Login User user) {
        DetailScheduleResponse schedule = scheduleService.findById(user, id);
        return CustomResponse.response(HttpStatus.OK, "일정을 정상적으로 조회했습니다.", schedule);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse findByDate(@RequestParam("date") String date, @Login User user) {
        List<AllScheduleResponse> schedules = scheduleService.findAllByDate(user, date);
        return CustomResponse.response(HttpStatus.OK, "일정을 정상적으로 조회했습니다.", schedules);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse deleteSchedule(@PathVariable("id") UUID id, @Login User user) {
        scheduleService.deleteSchedule(id, user);
        return CustomResponse.response(HttpStatus.OK, "일정을 정상적으로 삭제되었습니다.");
    }
}
