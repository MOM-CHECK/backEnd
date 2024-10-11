package com.example.mom_check.diary.controller;

import com.example.mom_check.common.annotation.Login;
import com.example.mom_check.common.response.CustomResponse;
import com.example.mom_check.diary.dto.CreateDiaryRequest;
import com.example.mom_check.diary.dto.DetailDiaryResponse;
import com.example.mom_check.diary.service.DiaryService;
import com.example.mom_check.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
