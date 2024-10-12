package com.example.mom_check.schedule.repository;

import com.example.mom_check.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
}
