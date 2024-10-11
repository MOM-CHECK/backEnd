package com.example.mom_check.diary.service;

import com.example.mom_check.diary.domain.Diary;
import com.example.mom_check.diary.dto.CreateDiaryRequest;
import com.example.mom_check.diary.dto.DetailDiaryResponse;
import com.example.mom_check.diary.repository.DiaryRepository;
import com.example.mom_check.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiaryService {
    private final DiaryRepository diaryRepository;

    @Transactional
    public DetailDiaryResponse createDiary(User user, CreateDiaryRequest req) {
        Diary diary = Diary.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .date(req.getDate())
                .icon(req.getIcon())
                .user(user)
                .build();

        diaryRepository.save(diary);

        return DetailDiaryResponse.toDto(diary);
    }
}
