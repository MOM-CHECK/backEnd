package com.example.mom_check.baby.service;

import com.example.mom_check.baby.domain.Baby;
import com.example.mom_check.baby.dto.CreateBabyRequest;
import com.example.mom_check.baby.dto.DetailBabyResponse;
import com.example.mom_check.baby.repository.BabyRepository;
import com.example.mom_check.common.type.SexType;
import com.example.mom_check.user.domain.User;
import com.example.mom_check.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BabyService {
    private final BabyRepository babyRepository;
    private final UserRepository userRepository;

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
        Baby baby = babyRepository.findByIdAndUser(id, user);
        return DetailBabyResponse.toDto(baby);
    }

    private SexType validateSexType(String sex) {
        return SexType.fromSexType(sex);
    }
}
