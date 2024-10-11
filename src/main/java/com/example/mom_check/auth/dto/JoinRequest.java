package com.example.mom_check.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JoinRequest {
    @NotBlank(message = "이메일을 입력하지 않았습니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "닉네임을 입력하지 않았습니다.")
    @Size(min=2, max=8, message = "2글지 이상, 8글자 이하로 작성해야 합니다.")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력하지 않았습니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$", message = "비밀번호는 영문, 숫자, 특수문자를 포함하여 8글자 이상, 16글자 이하로 작성해야 합니다.")
    private String password;

    @NotBlank(message = "보호자 휴대폰 번호를 입력하지 않았습니다.")
    @Pattern(regexp = "^[\\d]{11}$")
    private String partnerPhone;
}
