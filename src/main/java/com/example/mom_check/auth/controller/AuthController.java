package com.example.mom_check.auth.controller;

import com.example.mom_check.auth.dto.JoinRequest;
import com.example.mom_check.auth.dto.JoinResponse;
import com.example.mom_check.auth.service.AuthService;
import com.example.mom_check.common.response.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse join(@Validated @RequestBody JoinRequest req) {
        JoinResponse res = authService.join(req);
        return CustomResponse.response(HttpStatus.CREATED, "회원가입에 성공하셨습니다.", res);
    }
}
