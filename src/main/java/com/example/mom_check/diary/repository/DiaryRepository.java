package com.example.mom_check.diary.repository;

import com.example.mom_check.diary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiaryRepository extends JpaRepository<Diary, UUID> {
}
