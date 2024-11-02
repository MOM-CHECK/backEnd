package com.example.mom_check.mypage.service;

import com.example.mom_check.baby.domain.Baby;
import com.example.mom_check.baby.service.BabyService;
import com.example.mom_check.mypage.dto.MyPageResponse;
import com.example.mom_check.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyPageService {
    private final BabyService babyService;

    @Transactional
    public MyPageResponse getUserAndBabyInfo(User user) {
        Baby baby = babyService.findBabyByUser(user);
        return MyPageResponse.toDto(user, baby);
    }
}
