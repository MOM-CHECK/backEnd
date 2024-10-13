package com.example.mom_check.schedule.repository;

import com.example.mom_check.schedule.domain.Schedule;
import com.example.mom_check.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    Optional<Schedule> findByIdAndUser(UUID id, User user);
    Optional<List<Schedule>> findAllByDateAndUserOrderByTimeAsc(String date, User user);
}