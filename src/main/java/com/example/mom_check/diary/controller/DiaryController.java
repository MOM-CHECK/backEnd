package com.example.mom_check.diary.controller;

import com.example.mom_check.common.annotation.Login;
import com.example.mom_check.common.response.CustomResponse;
import com.example.mom_check.diary.dto.CreateDiaryRequest;
import com.example.mom_check.diary.dto.DetailDiaryResponse;
import com.example.mom_check.diary.dto.DiaryResponse;
import com.example.mom_check.diary.dto.UpdateDiaryRequest;
import com.example.mom_check.diary.service.DiaryService;
import com.example.mom_check.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/diaries")
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse createDiary(@Login User user, @RequestBody CreateDiaryRequest req) {
        DetailDiaryResponse diary = diaryService.createDiary(user, req);
        return CustomResponse.response(HttpStatus.CREATED, "일기를 정상적으로 작성했습니다.", diary);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse editDiary(@PathVariable("id") UUID id, @Validated @RequestBody UpdateDiaryRequest req, @Login User user) {
        DetailDiaryResponse diary = diaryService.editDiary(user, id, req);
        return CustomResponse.response(HttpStatus.OK, "일기를 정상적으로 수정했습니다.", diary);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse findById(@PathVariable("id") UUID id, @Login User user) {
        DetailDiaryResponse diary = diaryService.findById(user, id);
        return CustomResponse.response(HttpStatus.OK, "일기를 정상적으로 조회했습니다.", diary);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse findByDate(@RequestParam("date") String date, @Login User user) {
        DiaryResponse diary = diaryService.findByDate(user, date);
        if (diary != null) {
            return CustomResponse.response(HttpStatus.OK, "일기를 정상적으로 조회했습니다.", diary);
        } else {
            return CustomResponse.response(HttpStatus.OK, "일기를 정상적으로 조회했습니다.");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse deleteDiary(@PathVariable("id") UUID id, @Login User user) {
        diaryService.deleteDiary(id, user);
        return CustomResponse.response(HttpStatus.OK, "일기를 정상적으로 삭제되었습니다.");
    }
}
