package com.example.mom_check.weight.repository;

import com.example.mom_check.weight.domain.WeightGainRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WeightGainRecommendationRepository extends JpaRepository<WeightGainRecommendation, UUID> {
    Optional<WeightGainRecommendation> findByBmiAndWeek(String bmi, Integer week);
}
