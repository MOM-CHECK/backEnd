package com.example.mom_check.baby.controller;

import com.example.mom_check.baby.dto.CreateBabyRequest;
import com.example.mom_check.baby.dto.DetailBabyResponse;
import com.example.mom_check.baby.service.BabyService;
import com.example.mom_check.common.response.CustomResponse;
import com.example.mom_check.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/babies")
@RequiredArgsConstructor
public class BabyController {

    private final BabyService babyService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse createBaby(@Validated @RequestBody CreateBabyRequest req, User user) {
        DetailBabyResponse res = babyService.createBaby(user, req);
        return CustomResponse.response(HttpStatus.CREATED, "아기 정보를 정상적으로 작성했습니다.", res);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse findById(@PathVariable("id") UUID id, User user) {
        DetailBabyResponse res = babyService.findById(user, id);
        return CustomResponse.response(HttpStatus.OK, "정상적으로 아기 정보 상세 조회를 했습니다.", res);
    }
}
