package com.example.mom_check.weight.service;

import com.example.mom_check.common.exception.BusinessException;
import com.example.mom_check.common.type.WeightStatusType;
import com.example.mom_check.user.domain.User;
import com.example.mom_check.weight.domain.Weight;
import com.example.mom_check.weight.domain.WeightGainRecommendation;
import com.example.mom_check.weight.dto.*;
import com.example.mom_check.weight.repository.WeightGainRecommendationRepository;
import com.example.mom_check.weight.repository.WeightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.example.mom_check.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeightService {
    private final WeightRepository weightRepository;
    private final WeightGainRecommendationRepository recommendRepository;

    @Transactional
    public WeightResponse createWeight(User user, CreateWeightRequest req) {
        if (isExistDateWeight(req.getDate(), user)) {
            throw new BusinessException(DUPLICATED_DATE_WEIGHT);
        }

        Weight weight = Weight.builder()
                .date(req.getDate())
                .weight(req.getWeight())
                .user(user)
                .build();

        weightRepository.save(weight);

        return WeightResponse.toDto(weight);
    }

    @Transactional
    public List<AllWeightResponse> findAll(User user) {
        List<Weight> weights = findAllByUser(user);
        if (weights != null) {
            return weights.stream().map(AllWeightResponse::toDto).toList();
        }
        return null;
    }

    @Transactional
    public WeightResponse findById(UUID id, User user) {
        Weight weight = findByIdAndUser(id, user);
        return WeightResponse.toDto(weight);
    }

    @Transactional
    public WeightStatusResponse getWeightStatus(User user, Integer week, String bmi, WeightStatusRequest req) {
        if (week <= 0 & week > 40) {
            throw new BusinessException(WRONG_WEEK);
        }

        WeightGainRecommendation recommend = recommendRepository.findByBmiAndWeek(bmi, week)
                .orElseThrow(()-> new BusinessException(WEIGHT_RECOMMENDATION_NOT_FOUND));

        WeightStatusType status;
        if (recommend.getMin() <= req.getWeight() & req.getWeight() <= recommend.getMax()) {
            status = WeightStatusType.GOOD;
        } else if (recommend.getMin() > req.getWeight()) {
            status = WeightStatusType.UNDER;
        } else {
            status = WeightStatusType.OVER;
        }

        Weight weight = findByDateAndUser(req.getDate(), user);

        weight.update(status);

        return WeightStatusResponse.toDto(
                week,
                status,
                recommend.getMin(),
                recommend.getMax()
        );
    }

    private Boolean isExistDateWeight(String date, User user) {
        return weightRepository.existsByDateAndUser(date, user);
    }

    private Weight findByDateAndUser(String date, User user) {
        return weightRepository.findByDateAndUser(date, user)
                .orElseThrow(()->new BusinessException(WEIGHT_NOT_FOUND));
    }

    private Weight findByIdAndUser(UUID id, User user) {
        return weightRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new BusinessException(WEIGHT_NOT_FOUND));
    }

    private List<Weight> findAllByUser(User user) {
        return weightRepository.findAllByUserOrderByDateAsc(user)
                .orElse(null);
    }
}
