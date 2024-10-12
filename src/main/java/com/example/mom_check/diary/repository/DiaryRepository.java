package com.example.mom_check.diary.repository;

import com.example.mom_check.diary.domain.Diary;
import com.example.mom_check.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DiaryRepository extends JpaRepository<Diary, UUID> {
    Optional<Diary> findByIdAndUser(UUID id, User user);
    Optional<Diary> findByDateAndUser(String date, User user);
}
