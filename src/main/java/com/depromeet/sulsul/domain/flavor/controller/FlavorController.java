package com.depromeet.sulsul.domain.flavor.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.flavor.dto.FlavorResponse;
import com.depromeet.sulsul.domain.flavor.service.FlavorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/flavors")
public class FlavorController {

    private final FlavorService flavorService;

    @GetMapping
    public ResponseDto<List<FlavorResponse>> findAll() {
        return ResponseDto.of(flavorService.findAll());
    }
}
