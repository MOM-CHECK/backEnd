package com.example.mom_check.weight.controller;

import com.example.mom_check.common.annotation.Login;
import com.example.mom_check.common.response.CustomResponse;
import com.example.mom_check.user.domain.User;
import com.example.mom_check.weight.dto.*;
import com.example.mom_check.weight.service.WeightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/weight")
@RequiredArgsConstructor
public class WeightController {
    private final WeightService weightService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse createWeight(@Validated @RequestBody CreateWeightRequest req, @Login User user) {
        WeightResponse weight = weightService.createWeight(user, req);
        return CustomResponse.response(HttpStatus.CREATED, "몸무게를 정상적으로 작성했습니다.", weight);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse findAll(@Login User user) {
        List<AllWeightResponse> weights = weightService.findAll(user);
        return CustomResponse.response(HttpStatus.OK, "몸무게를 정상적으로 조회했습니다.", weights);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse findById(@PathVariable("id") UUID id, @Login User user) {
        WeightResponse weight = weightService.findById(id, user);
        return CustomResponse.response(HttpStatus.OK, "몸무게를 정상적으로 상세 조회했습니다.", weight);
    }

    @GetMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse getWeightStatus(@RequestParam("week") Integer week, @Validated @RequestBody WeightStatusRequest req, @Login User user) {
        WeightStatusResponse status = weightService.getWeightStatus(user, week, req);
        return CustomResponse.response(HttpStatus.OK, "몸무게의 적정량을 정상적으로 조회했습니다.", status);
    }
}
