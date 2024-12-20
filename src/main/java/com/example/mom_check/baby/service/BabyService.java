package com.example.mom_check.baby.service;

import com.example.mom_check.baby.domain.Baby;
import com.example.mom_check.baby.dto.CreateBabyRequest;
import com.example.mom_check.baby.dto.DetailBabyResponse;
import com.example.mom_check.baby.repository.BabyRepository;
import com.example.mom_check.common.exception.BusinessException;
import com.example.mom_check.common.type.SexType;
import com.example.mom_check.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.example.mom_check.common.exception.ErrorCode.BABY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BabyService {
    private final BabyRepository babyRepository;

    @Transactional
    public DetailBabyResponse createBaby(User user, CreateBabyRequest req) {
        SexType sex = validateSexType(req.getSex());

        Baby baby = Baby.builder()
                .name(req.getName())
                .expectedBirth(req.getExpectedBirth())
                .sex(sex)
                .user(user)
                .build();

        babyRepository.save(baby);

        return DetailBabyResponse.toDto(baby);
    }

    @Transactional
    public DetailBabyResponse findById(User user, UUID id) {
        Baby baby = babyRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new BusinessException(BABY_NOT_FOUND));
        return DetailBabyResponse.toDto(baby);
    }

    public Baby findBabyByUser(User user) {
        return babyRepository.findByUser(user)
                .orElseThrow(() -> new BusinessException(BABY_NOT_FOUND));
    }

    private SexType validateSexType(String sex) {
        return SexType.fromSexType(sex);
    }
}
