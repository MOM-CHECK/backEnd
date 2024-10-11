package com.example.mom_check.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "이메일을 입력하지 않았습니다.")
    @Email(message="이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력하지 않았습니다.")
    private String password;
}
