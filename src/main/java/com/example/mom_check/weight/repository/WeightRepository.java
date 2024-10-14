package com.example.mom_check.weight.repository;

import com.example.mom_check.user.domain.User;
import com.example.mom_check.weight.domain.Weight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WeightRepository extends JpaRepository<Weight, UUID> {
    Optional<List<Weight>> findAllByUserOrderByDateAsc(User user);
    Optional<Weight> findByIdAndUser(UUID id, User user);
    Optional<Weight> findByDateAndUser(String date, User user);
    Boolean existsByDateAndUser(String date, User user);
}
