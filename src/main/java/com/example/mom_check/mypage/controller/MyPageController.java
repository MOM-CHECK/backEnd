package com.example.mom_check.mypage.controller;

import com.example.mom_check.common.annotation.Login;
import com.example.mom_check.common.response.CustomResponse;
import com.example.mom_check.mypage.dto.MyPageResponse;
import com.example.mom_check.mypage.service.MyPageService;
import com.example.mom_check.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse getMyPage(@Login User user) {
        MyPageResponse res = myPageService.getUserAndBabyInfo(user);
        return CustomResponse.response(HttpStatus.OK, "정상적으로 마이페이지 정보 조회를 했습니다.", res);
    }

}
