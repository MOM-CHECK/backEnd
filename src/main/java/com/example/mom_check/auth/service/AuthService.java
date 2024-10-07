package com.example.mom_check.auth.service;

import com.example.mom_check.auth.dto.JoinRequest;
import com.example.mom_check.auth.dto.JoinResponse;
import com.example.mom_check.common.exception.BusinessException;
import com.example.mom_check.user.domain.User;
import com.example.mom_check.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.mom_check.common.exception.ErrorCode.DUPLICATED_EMAIL;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public JoinResponse join(JoinRequest req) {
        User user = User.builder()
                .email(req.getEmail())
                .nickname(req.getNickname())
                .password(encoder.encode(req.getPassword()))
                .partnerPhone(req.getPartnerPhone())
                .build();

        validateDuplicateEmail(req.getEmail());
        
        // 로그인 API 추가하면서 accessToken도 함께 전달 예정
        return JoinResponse.toDto(user);
    }

    @Transactional
    public void validateDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException(DUPLICATED_EMAIL);
        }
    }
}
