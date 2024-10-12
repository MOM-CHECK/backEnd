package com.example.mom_check.diary.service;

import com.example.mom_check.common.exception.BusinessException;
import com.example.mom_check.diary.domain.Diary;
import com.example.mom_check.diary.dto.CreateDiaryRequest;
import com.example.mom_check.diary.dto.DetailDiaryResponse;
import com.example.mom_check.diary.dto.DiaryResponse;
import com.example.mom_check.diary.dto.UpdateDiaryRequest;
import com.example.mom_check.diary.repository.DiaryRepository;
import com.example.mom_check.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.example.mom_check.common.exception.ErrorCode.*;

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

    @Transactional
    public DetailDiaryResponse findById(User user, UUID id) {
        Diary diary = findByIdAndUser(id, user);
        return DetailDiaryResponse.toDto(diary);
    }

    @Transactional
    public DiaryResponse findByDate(User user, String date) {
        Diary diary = findByDateAndUser(date, user);
        if (diary != null) {
            return DiaryResponse.toDto(diary);
        } else {
            return null;
        }

    }

    @Transactional
    public DetailDiaryResponse editDiary(User user, UUID id, UpdateDiaryRequest req) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new BusinessException(DIARY_NOT_FOUND));
        validateAuthor(diary, user);

        diary.update(
                req.getTitle(),
                req.getIcon(),
                req.getContent()
        );

        return DetailDiaryResponse.toDto(diary);
    }

    private void validateAuthor(Diary diary, User user) {
        if (!isAuthor(diary, user)) {
            throw new BusinessException(UNAUTHORIZED_MEMBER);
        }
    }

    private Boolean isAuthor(Diary diary, User user) {
        return user.equals(diary.getUser());
    }

    private Diary findByIdAndUser(UUID id, User user) {
        return diaryRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new BusinessException(DIARY_NOT_FOUND));
    }

    private Diary findByDateAndUser(String date, User user) {
        return diaryRepository.findByDateAndUser(date, user)
                .orElse(null);
    }
}
