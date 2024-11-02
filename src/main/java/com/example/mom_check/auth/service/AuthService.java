package com.example.mom_check.auth.service;

import com.example.mom_check.auth.dto.JoinRequest;
import com.example.mom_check.auth.dto.JoinResponse;
import com.example.mom_check.auth.dto.LoginRequest;
import com.example.mom_check.auth.dto.LoginResponse;
import com.example.mom_check.baby.domain.Baby;
import com.example.mom_check.baby.service.BabyService;
import com.example.mom_check.common.config.jwt.TokenProvider;
import com.example.mom_check.common.exception.BusinessException;
import com.example.mom_check.user.domain.User;
import com.example.mom_check.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.UUID;

import static com.example.mom_check.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final BabyService babyService;
    private final BCryptPasswordEncoder encoder;
    private final TokenProvider tokenProvider;

    @Transactional
    public JoinResponse join(JoinRequest req) {
        User user = User.builder()
                .email(req.getEmail())
                .nickname(req.getNickname())
                .password(encoder.encode(req.getPassword()))
                .partnerPhone(req.getPartnerPhone())
                .build();

        validateDuplicateEmail(req.getEmail());

        userService.saveUser(user);

        String accessToken = getAccessToken(user);

        return JoinResponse.toDto(user, accessToken);
    }

    private void validateDuplicateEmail(String email) {
        if (userService.isDuplicateEmail(email)) {
            throw new BusinessException(DUPLICATED_EMAIL);
        }
    }

    @Transactional
    public LoginResponse logIn(LoginRequest req) {
        User user = userService.findByEmail(req.getEmail());
        if(!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new BusinessException(WRONG_PASSWORD);
        }
        String accessToken = getAccessToken(user);
        Baby baby = babyService.findBabyByUser(user);
        return LoginResponse.toDto(user, accessToken, baby);
    }

    private String getAccessToken(User user) {
        return tokenProvider.generateToken(Duration.ofDays(30), user);
    }

    @Transactional
    public User findMemberByToken(String token) {
        UUID userId = tokenProvider.getMemberId(token);
        return userService.findById(userId);
    }
}
