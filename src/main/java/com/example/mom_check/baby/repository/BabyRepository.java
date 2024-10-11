package com.example.mom_check.baby.repository;

import com.example.mom_check.baby.domain.Baby;
import com.example.mom_check.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface BabyRepository extends JpaRepository<Baby, UUID> {
    Optional<Baby> findByIdAndUser(UUID id, User user);
    Optional<Baby> findByUser(User user);
}
