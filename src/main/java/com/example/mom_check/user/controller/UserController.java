package com.example.mom_check.user.controller;

import com.example.mom_check.common.annotation.Login;
import com.example.mom_check.common.response.CustomResponse;
import com.example.mom_check.user.domain.User;
import com.example.mom_check.user.dto.InitialPhysicalRequest;
import com.example.mom_check.user.dto.InitialPhysicalResponse;
import com.example.mom_check.user.dto.UserInfoResponse;
import com.example.mom_check.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse findUser(@PathVariable("id") UUID id, @Login User user) {
        UserInfoResponse res = userService.findUser(id, user);
        return CustomResponse.response(HttpStatus.OK, "사용자의 정보를 정상적으로 조회했습니다.", res);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse validateEmailDuplicated(@RequestParam("email") String email) {
        userService.isDuplicatedEmail(email);
        return CustomResponse.response(HttpStatus.OK, "이메일 중복 확인을 정상적으로 마쳤습니다.");
    }

    @PostMapping("/initial-physical")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse createInitialPhysical(@Validated @RequestBody InitialPhysicalRequest req, @Login User user) {
        InitialPhysicalResponse res = userService.createInitialPhysical(user, req);
        return CustomResponse.response(HttpStatus.CREATED, "임신 전 데이터를 정상적으로 저장했습니다.", res);
    }

    @GetMapping("/initial-physical")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse getInitialPhysical(@Login User user) {
        InitialPhysicalResponse res = userService.getInitialPhysical(user);
        return CustomResponse.response(HttpStatus.CREATED, "임신 전 데이터를 정상적으로 조회했습니다.", res);
    }
}
