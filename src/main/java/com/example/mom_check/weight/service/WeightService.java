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

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.example.mom_check.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeightService {
    private final WeightRepository weightRepository;
    private final WeightGainRecommendationRepository recommendRepository;

    private static final String UNDER = "under";
    private static final String NORMAL = "normal";
    private static final String OBESITY = "obesity";

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
    public WeightStatusResponse getWeightStatus(User user, Integer week, WeightStatusRequest req) {
        validateWeek(week);

        Double initialBMI = user.getInitialPhysical().getBmi();
        String BMIStatus = getBMIStatus(initialBMI);

        WeightGainRecommendation recommend = getWeightRecommendation(BMIStatus, week);

        Weight myWeightInfo = findByDateAndUser(req.getDate(), user);

        Double currentWeight = myWeightInfo.getWeight();
        Double initialWeight = getInitialWeight(user);
        Double weightDifference = currentWeight - initialWeight;

        WeightStatusType currentStatus = getWeightStatus(recommend, weightDifference);

        myWeightInfo.update(currentStatus);

        return WeightStatusResponse.toDto(
                week,
                myWeightInfo.getWeight(),
                currentStatus,
                recommend.getMin(),
                recommend.getMax()
        );
    }

    private WeightStatusType getWeightStatus(WeightGainRecommendation recommend, Double weightDifference) {
        Double min = recommend.getMin();
        Double max = recommend.getMax();

        if (min <= weightDifference & weightDifference <= max) {
            return WeightStatusType.GOOD;
        } else if (min > weightDifference) {
            return WeightStatusType.UNDER;
        }
        return WeightStatusType.OVER;
    }

    private Double getInitialWeight(User user) {
        return user.getInitialPhysical().getWeight();
    }

    private void validateWeek(Integer week) {
        if (week <= 0 & week > 40) {
            throw new BusinessException(WRONG_WEEK);
        }
    }

    private WeightGainRecommendation getWeightRecommendation(String BMIStatus, Integer week) {
        return recommendRepository.findByBmiAndWeek(BMIStatus, week)
                .orElseThrow(()-> new BusinessException(WEIGHT_RECOMMENDATION_NOT_FOUND));
    }

    private String getBMIStatus(Double bmi) {
        if (bmi < 18.5) {
            return UNDER;
        } else if (bmi < 25) {
            return NORMAL;
        }
        return OBESITY;
    }

    private Boolean isExistDateWeight(LocalDate date, User user) {
        return weightRepository.existsByDateAndUser(date, user);
    }

    private Weight findByDateAndUser(LocalDate date, User user) {
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
