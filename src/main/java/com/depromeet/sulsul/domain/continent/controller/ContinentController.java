package com.depromeet.sulsul.domain.continent.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.continent.dto.ContinentDto;
import com.depromeet.sulsul.domain.continent.service.ContinentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "대륙 APIs (version 1)")
@RequestMapping("/api/v1/continents")
public class ContinentController {

  private final ContinentService continentService;

  @GetMapping
  @ApiOperation(value = "대륙 전체 조회 API")
  public ResponseDto<List<ContinentDto>> findAll() {
    return ResponseDto.from(continentService.findAll());
  }
}
