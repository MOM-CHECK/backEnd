package com.example.mom_check.user.service;

import com.example.mom_check.user.domain.InitialPhysical;
import com.example.mom_check.user.domain.User;
import com.example.mom_check.user.dto.InitialPhysicalRequest;
import com.example.mom_check.user.dto.InitialPhysicalResponse;
import com.example.mom_check.user.dto.UserInfoResponse;
import com.example.mom_check.user.repository.InitialPhysicalRepository;
import com.example.mom_check.user.repository.UserRepository;
import com.example.mom_check.common.exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.mom_check.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final InitialPhysicalRepository initialPhysicalRepository;

    @Transactional
    public UserInfoResponse findUser(UUID id, User loggedInUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

        validateUser(user, loggedInUser);

        return UserInfoResponse.toDto(loggedInUser);
    }

    private void validateUser(User user, User loggedInUser) {
        if (user != loggedInUser) {
            throw new BusinessException(UNAUTHORIZED_MEMBER);
        }
    }

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

    @Transactional
    public InitialPhysicalResponse createInitialPhysical(User user, InitialPhysicalRequest req) {
        InitialPhysical physical = InitialPhysical.builder()
                .height(req.getHeight())
                .weight(req.getWeight())
                .bmi(req.getBmi())
                .user(user)
                .build();

        initialPhysicalRepository.save(physical);

        return InitialPhysicalResponse.toDto(user, physical);
    }

    @Transactional
    public InitialPhysicalResponse getInitialPhysical(User user) {
        InitialPhysical physical = initialPhysicalRepository.findById(user.getId())
                .orElseThrow(() -> new BusinessException(INITIAL_PHYSICAL_NOT_FOUND));
        return InitialPhysicalResponse.toDto(user, physical);
    }
}
