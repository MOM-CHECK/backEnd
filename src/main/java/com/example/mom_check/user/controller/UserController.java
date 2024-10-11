package com.example.mom_check.user.controller;

import com.example.mom_check.common.response.CustomResponse;
import com.example.mom_check.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse validateEmailDuplicated(@RequestParam String email) {
        userService.isDuplicatedEmail(email);
        return CustomResponse.response(HttpStatus.OK, "이메일 중복 확인을 정상적으로 마쳤습니다.");
    }
}
