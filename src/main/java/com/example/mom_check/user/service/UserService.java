package com.example.mom_check.user.service;

import com.example.mom_check.user.domain.User;
import com.example.mom_check.user.repository.UserRepository;
import com.example.mom_check.common.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.mom_check.common.exception.ErrorCode.DUPLICATED_EMAIL;
import static com.example.mom_check.common.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
    }

    @Transactional
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));
    }

    @Transactional
    public void isDuplicatedEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException(DUPLICATED_EMAIL);
        }
    }
}
